package com.tapnexempire.repository

import com.tapnexempire.service.AuthService
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authService: AuthService
) {

    val isLoggedIn = authService.isLoggedIn
    val phoneNumber = authService.phoneNumber

    fun sendOtp(phone: String) {
        authService.sendOtp(phone)
    }

    fun verifyOtp(otp: String): Boolean {
        return authService.verifyOtp(otp)
    }

    fun logout() {
        authService.logout()
    }
}
