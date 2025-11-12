package com.tapnexempire.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    // ✅ App login state
    val isLoggedIn = mutableStateOf(false)

    // ✅ OTP-related states
    val isOtpSent = mutableStateOf(false)
    val isVerified = mutableStateOf(false)
    val phoneNumber = mutableStateOf("")
    val otpCode = mutableStateOf("")

    // 🚀 Send OTP simulation
    fun sendOtp(phone: String) {
        viewModelScope.launch {
            phoneNumber.value = phone
            isOtpSent.value = true
            delay(1000) // simulate API delay
        }
    }

    // 🚀 Verify OTP simulation
    fun verifyOtp(otp: String) {
        viewModelScope.launch {
            delay(1000) // simulate verification delay
            if (otp == "1234") { // Mock OTP for now
                isVerified.value = true
                isLoggedIn.value = true
            } else {
                isVerified.value = false
            }
        }
    }

    fun logout() {
        isLoggedIn.value = false
        isOtpSent.value = false
        isVerified.value = false
        phoneNumber.value = ""
        otpCode.value = ""
    }
}
