package com.tapnexempire.repository

import com.tapnexempire.service.AuthService

class AuthRepository(private val authService: AuthService) {

    suspend fun login(phoneNumber: String, password: String): Boolean {
        return authService.login(phoneNumber, password)
    }

    suspend fun signup(name: String, phoneNumber: String, password: String): Boolean {
        return authService.signup(name, phoneNumber, password)
    }

    suspend fun verifyOtp(phoneNumber: String, otp: String): Boolean {
        return authService.verifyOtp(phoneNumber, otp)
    }
}p
