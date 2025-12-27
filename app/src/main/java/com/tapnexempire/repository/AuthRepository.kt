package com.tapnexempire.repository

import android.app.Activity
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {

    // SEND OTP
    fun sendOtp(
        activity: Activity,
        phoneNumber: String,
        onCodeSent: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    // Auto verification (optional handle later)
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    onFailure(e.message ?: "OTP verification failed")
                }

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    onCodeSent(verificationId)
                }
            })
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    // VERIFY OTP
    suspend fun verifyOtp(
        verificationId: String,
        otp: String
    ): Boolean {
        val credential = PhoneAuthProvider.getCredential(verificationId, otp)
        val result = auth.signInWithCredential(credential).await()
        val user = result.user ?: return false

        val userId = user.uid

        // USERS COLLECTION
        val userDoc = firestore.collection("users").document(userId)
        if (!userDoc.get().await().exists()) {
            userDoc.set(
                mapOf(
                    "phone" to user.phoneNumber,
                    "createdAt" to System.currentTimeMillis()
                )
            ).await()
        }

        // WALLET COLLECTION (IMPORTANT)
        val walletDoc = firestore.collection("wallets").document(userId)
        if (!walletDoc.get().await().exists()) {
            walletDoc.set(
                mapOf(
                    "coins" to 0,
                    "withdrawableCoins" to 0
                )
            ).await()
        }

        return true
    }

    fun getCurrentUserId(): String? = auth.currentUser?.uid

    fun signOut() = auth.signOut()
}
