package com.tapnexempire.service

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthService {

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber: StateFlow<String> = _phoneNumber

    fun sendOtp(phone: String) {
        // 🔐 Future: Firebase OTP
        _phoneNumber.value = phone
    }

    fun verifyOtp(otp: String): Boolean {
        // 🔐 Future: Firebase verification
        _isLoggedIn.value = true
        return true
    }

    fun logout() {
        _isLoggedIn.value = false
        _phoneNumber.value = ""
    }
}
