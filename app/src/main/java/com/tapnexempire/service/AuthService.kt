package com.tapnexempire.service

class AuthService {

    // Simulated database for demo purpose
    private val registeredUsers = mutableMapOf<String, String>() // phone -> otp

    // Send OTP (simulated)
    fun sendOtp(phone: String): Boolean {
        val otp = "1234" // Dummy OTP for testing
        registeredUsers[phone] = otp
        println("OTP sent to $phone: $otp")
        return true
    }

    // Verify OTP
    fun verifyOtp(phone: String, otp: String): Boolean {
        val correctOtp = registeredUsers[phone]
        return otp == correctOtp
    }

    // Register user
    fun registerUser(phone: String): Boolean {
        if (!registeredUsers.containsKey(phone)) {
            registeredUsers[phone] = "1234"
            println("User registered: $phone")
            return true
        }
        return false
    }

    // Logout simulation
    fun logout(phone: String) {
        registeredUsers.remove(phone)
        println("User logged out: $phone")
    }
}
