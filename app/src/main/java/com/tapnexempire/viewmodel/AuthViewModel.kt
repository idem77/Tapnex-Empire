package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    val isLoggedIn: StateFlow<Boolean> = repository.isLoggedIn
    val phoneNumber: StateFlow<String> = repository.phoneNumber

    fun sendOtp(phone: String) {
        viewModelScope.launch {
            repository.sendOtp(phone)
        }
    }

    fun verifyOtp(otp: String): Boolean {
        var result = false
        viewModelScope.launch {
            result = repository.verifyOtp(otp)
        }
        return result
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}
