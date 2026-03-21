package com.tapnexempire.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: AuthRepository
) : ViewModel() {

    private val _authState = MutableStateFlow<UiState<Boolean>>(UiState.Success(false))
    val authState: StateFlow<UiState<Boolean>> = _authState

    private val _otpState = MutableStateFlow<String?>(null)
    val otpState: StateFlow<String?> = _otpState

    fun sendOtp(
        activity: Activity,
        phone: String,
        onError: (String) -> Unit
    ) {
        _authState.value = UiState.Loading

        repo.sendOtp(
            activity,
            phone,
            onCodeSent = { _otpState.value = it },
            onFailure = {
                _authState.value = UiState.Error(it)
                onError(it)
            }
        )
    }

    fun verifyOtp(verificationId: String, otp: String) {
        viewModelScope.launch {
            _authState.value = UiState.Loading
            try {
                val success = repo.verifyOtp(verificationId, otp)
                _authState.value = UiState.Success(success)
            } catch (e: Exception) {
                _authState.value = UiState.Error(e.message ?: "Auth failed")
            }
        }
    }

    fun logout() = repo.signOut()
}
