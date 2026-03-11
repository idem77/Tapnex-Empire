package com.tapnexempire.data.repository

import android.app.Activity
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.FirebaseException
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import com.tapnexempire.models.WalletModel

class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {

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

                override fun onVerificationCompleted(credential: PhoneAuthCredential) {}

                override fun onVerificationFailed(e: FirebaseException) {
                    onFailure(e.message ?: "OTP failed")
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

    suspend fun verifyOtp(
        verificationId: String,
        otp: String
    ): Boolean {

        val credential = PhoneAuthProvider.getCredential(verificationId, otp)
        val result = auth.signInWithCredential(credential).await()
        val user = result.user ?: return false
        val userId = user.uid

        val userDoc = firestore.collection("users").document(userId)
        if (!userDoc.get().await().exists()) {
            userDoc.set(
                mapOf(
                    "phone" to user.phoneNumber,
                    "createdAt" to System.currentTimeMillis()
                )
            ).await()
        }

        val walletDoc = firestore.collection("wallets").document(userId)
        if (!walletDoc.get().await().exists()) {
            walletDoc.set(WalletModel(userId = userId)).await()
        }

        return true
    }

    fun getCurrentUserId(): String? = auth.currentUser?.uid
    fun signOut() = auth.signOut()
}
