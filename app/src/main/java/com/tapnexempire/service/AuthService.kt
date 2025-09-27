package com.tapnexempire.service

object AuthService {
    fun login(phone: String, password: String): Boolean {
        // Later: call API
        return phone == "9999999999" && password == "1234"
    }

    fun signup(phone: String, password: String): Boolean {
        // Later: call API
        return true
    }

    fun verifyOtp(phone: String, otp: String): Boolean {
        // Later: call API
        return otp == "1234"
    }
}
