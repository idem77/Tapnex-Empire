package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    fun sendOtp(phone: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            onResult(repository.sendOtp(phone))
        }
    }

    fun verifyOtp(phone: String, otp: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            onResult(repository.verifyOtp(phone, otp))
        }
    }

    fun isLoggedIn(): Boolean = repository.isLoggedIn()

    fun logout() = repository.logout()
}
