package com.tapnexempire.repository

import android.app.Activity
import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private var verificationId: String? = null

    fun sendOtp(phoneNumber: String, activity: Activity, onCodeSent: () -> Unit, onFailure: (String) -> Unit) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity) // ✅ FIXED – pass actual activity, not null
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    // Auto verification
                    signInWithCredential(credential, onFailure)
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    Log.e("AuthRepository", "Verification failed: ${e.message}")
                    onFailure(e.message ?: "Verification failed")
                }

                override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                    this@AuthRepository.verificationId = verificationId
                    onCodeSent()
                }
            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun verifyOtp(otpCode: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val id = verificationId
        if (id == null) {
            onFailure("No verification ID")
            return
        }
        val credential = PhoneAuthProvider.getCredential(id, otpCode)
        signInWithCredential(credential, onFailure, onSuccess)
    }

    private fun signInWithCredential(
        credential: PhoneAuthCredential,
        onFailure: (String) -> Unit,
        onSuccess: (() -> Unit)? = null
    ) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess?.invoke()
                } else {
                    onFailure(task.exception?.message ?: "Sign-in failed")
                }
            }
    }

    fun isUserLoggedIn(): Boolean = auth.currentUser != null
    fun logout() = auth.signOut()
}
