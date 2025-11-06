package com.tapnexempire.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.tapnexempire.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    val isLoggedIn: MutableState<Boolean> = mutableStateOf(authRepository.isLoggedIn())
    var verificationId: String? = null

    val isLoading: MutableState<Boolean> = mutableStateOf(false)
    val otpCode: MutableState<String> = mutableStateOf("")

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: com.google.firebase.auth.PhoneAuthCredential) {
            // Auto verification
        }

        override fun onVerificationFailed(e: com.google.firebase.FirebaseException) {
            isLoading.value = false
        }

        override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
            this@AuthViewModel.verificationId = verificationId
            isLoading.value = false
        }
    }

    fun sendOtp(phoneNumber: String, onOtpSent: () -> Unit) {
        isLoading.value = true
        authRepository.sendOtp(phoneNumber, callbacks)
        onOtpSent()
    }

    fun verifyOtp(onSuccess: () -> Unit, onError: (String) -> Unit) {
        val code = otpCode.value
        val id = verificationId
        if (id != null && code.isNotEmpty()) {
            isLoading.value = true
            viewModelScope.launch {
                try {
                    authRepository.verifyOtp(id, code)
                    isLoggedIn.value = true
                    onSuccess()
                } catch (e: Exception) {
                    isLoading.value = false
                    onError(e.message ?: "OTP Verification failed")
                }
            }
        } else {
            onError("OTP or Verification ID is missing")
        }
    }

    fun logout() {
        authRepository.logout()
        isLoggedIn.value = false
    }
}
