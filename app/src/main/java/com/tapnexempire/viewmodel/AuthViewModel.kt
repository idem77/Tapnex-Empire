package com.tapnexempire.viewmodel

import android.app.Activity
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
    private val repo: AuthRepository
) : ViewModel() {

    private val _otpState = MutableStateFlow<String?>(null)
    val otpState: StateFlow<String?> = _otpState

    private val _authSuccess = MutableStateFlow(false)
    val authSuccess: StateFlow<Boolean> = _authSuccess

    fun sendOtp(
        activity: Activity,
        phone: String,
        onError: (String) -> Unit
    ) {
        repo.sendOtp(
            activity,
            phone,
            onCodeSent = { _otpState.value = it },
            onFailure = onError
        )
    }

    fun verifyOtp(verificationId: String, otp: String) {
        viewModelScope.launch {
            _authSuccess.value = repo.verifyOtp(verificationId, otp)
        }
    }

    fun logout() = repo.signOut()
}
