package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.FirebaseException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class AuthViewModel : ViewModel() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _verificationId = MutableStateFlow<String?>(null)
    val verificationId: StateFlow<String?> = _verificationId

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _otpSent = MutableStateFlow(false)
    val otpSent: StateFlow<Boolean> = _otpSent

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess: StateFlow<Boolean> = _loginSuccess

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    /**
     * 🔹 Send OTP to user’s phone number
     */
    fun sendOtp(phoneNumber: String, activity: android.app.Activity) {
        _isLoading.value = true
        _errorMessage.value = null

        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    _isLoading.value = false
                    signInWithCredential(credential)
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    _isLoading.value = false
                    _errorMessage.value = e.localizedMessage ?: "Verification failed"
                }

                override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                    _isLoading.value = false
                    _otpSent.value = true
                    _verificationId.value = verificationId
                }
            })
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    /**
     * 🔹 Verify OTP and login
     */
    fun verifyOtp(otpCode: String) {
        val verificationId = _verificationId.value ?: return
        _isLoading.value = true

        val credential = PhoneAuthProvider.getCredential(verificationId, otpCode)
        signInWithCredential(credential)
    }

    /**
     * 🔹 Sign in with credential
     */
    private fun signInWithCredential(credential: PhoneAuthCredential) {
        viewModelScope.launch {
            firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    _isLoading.value = false
                    if (task.isSuccessful) {
                        _loginSuccess.value = true
                    } else {
                        _errorMessage.value = task.exception?.localizedMessage ?: "Login failed"
                    }
                }
        }
    }

    fun logout() {
        firebaseAuth.signOut()
        _loginSuccess.value = false
    }
}
