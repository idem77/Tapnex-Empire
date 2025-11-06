package com.tapnexempire.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class AuthRepository(private val auth: FirebaseAuth) {

    // ✅ Send OTP
    fun sendOtp(
        phoneNumber: String,
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    ) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(null) // will be handled in Compose context
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    // ✅ Verify OTP manually
    fun verifyOtp(verificationId: String, otpCode: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val credential = PhoneAuthProvider.getCredential(verificationId, otpCode)
        auth.signInWithCredential(credential)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { e -> onFailure(e) }
    }

    // ✅ Check login state
    fun isLoggedIn(): Boolean = auth.currentUser != null

    // ✅ Logout
    fun logout() {
        auth.signOut()
    }
}
