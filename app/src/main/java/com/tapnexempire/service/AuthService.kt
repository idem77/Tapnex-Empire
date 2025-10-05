package com.tapnexempire.service

object AuthService {
    fun login(phone: String, password: String): Boolean {
        return phone == "9999999999" && password == "1234"
    }

    fun signup(phone: String, password: String): Boolean {
        return true
    }

    fun verifyOtp(phone: String, otp: String): Boolean {
        return otp == "1234"
    }
}
