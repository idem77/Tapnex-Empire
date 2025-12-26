package com.tapnexempire.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit

class AuthRepository(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {

    // Send OTP
    fun sendOtp(phoneNumber: String, onCodeSent: (String) -> Unit, onFailure: (String) -> Unit) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(/* provide current activity if needed */)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    // Auto verification completed
                }

                override fun onVerificationFailed(e: Exception) {
                    onFailure(e.message ?: "Verification failed")
                }

                override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                    onCodeSent(verificationId)
                }
            })
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    // Verify OTP
    suspend fun verifyOtp(verificationId: String, otp: String): Boolean {
        val credential = PhoneAuthProvider.getCredential(verificationId, otp)
        val result = auth.signInWithCredential(credential).await()
        val user = result.user ?: return false

        // Create user document if not exist
        val userDoc = firestore.collection("users").document(user.uid)
        if (!(userDoc.get().await().exists())) {
            userDoc.set(mapOf("coins" to 0)).await()
        }
        return true
    }

    fun getCurrentUserId(): String? = auth.currentUser?.uid

    fun signOut() = auth.signOut()
}
