package com.tapnexempire.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    val phoneNumber = mutableStateOf("")
    val otp = mutableStateOf("")
    val verificationId = mutableStateOf<String?>(null)

    val isOtpSent = mutableStateOf(false)
    val isVerifying = mutableStateOf(false)
    val isLoggedIn = mutableStateOf(firebaseAuth.currentUser != null)
    val errorMessage = mutableStateOf<String?>(null)

    // 🔹 Step 1: Send OTP
    fun sendOtp(activity: android.app.Activity) {
        val number = phoneNumber.value.trim()
        if (number.isEmpty() || number.length < 10) {
            errorMessage.value = "Enter valid phone number"
            return
        }

        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber("+91$number") // 🇮🇳 change prefix if needed
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    // Auto verification
                    signInWithCredential(credential)
                }

                override fun onVerificationFailed(e: Exception) {
                    errorMessage.value = e.message
                    isOtpSent.value = false
                }

                override fun onCodeSent(
                    verificationIdParam: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    verificationId.value = verificationIdParam
                    isOtpSent.value = true
                }
            })
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    // 🔹 Step 2: Verify OTP
    fun verifyOtp() {
        val id = verificationId.value
        val code = otp.value.trim()
        if (id.isNullOrEmpty() || code.isEmpty()) {
            errorMessage.value = "Invalid OTP or Verification ID"
            return
        }

        isVerifying.value = true
        val credential = PhoneAuthProvider.getCredential(id, code)
        signInWithCredential(credential)
    }

    private fun signInWithCredential(credential: PhoneAuthCredential) {
        viewModelScope.launch {
            firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    isVerifying.value = false
                    if (task.isSuccessful) {
                        isLoggedIn.value = true
                    } else {
                        errorMessage.value = task.exception?.message
                    }
                }
        }
    }

    fun logout() {
        firebaseAuth.signOut()
        isLoggedIn.value = false
    }
}
