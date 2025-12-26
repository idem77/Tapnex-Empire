package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _otpState = MutableStateFlow("")
    val otpState: StateFlow<String> get() = _otpState

    private val _authState = MutableStateFlow(false)
    val authState: StateFlow<Boolean> get() = _authState

    fun sendOtp(phoneNumber: String, onCodeSent: (String) -> Unit, onError: (String) -> Unit) {
        repository.sendOtp(phoneNumber, { code ->
            _otpState.value = code
            onCodeSent(code)
        }, { error ->
            onError(error)
        })
    }

    fun verifyOtp(verificationId: String, otp: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val result = repository.verifyOtp(verificationId, otp)
                _authState.value = result
                if (result) onSuccess() else onError("Verification failed")
            } catch (e: Exception) {
                onError(e.message ?: "Unknown error")
            }
        }
    }

    fun signOut() {
        repository.signOut()
        _authState.value = false
    }
}
