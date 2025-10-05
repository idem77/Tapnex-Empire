package com.tapnexempire.service

object AuthService {
    private var currentUserPhone: String = ""
    private var currentUserName: String = "User"

    fun login(phone: String) {
        currentUserPhone = phone
        // Simulate backend login
    }

    fun signup(phone: String) {
        currentUserPhone = phone
        // Simulate backend signup
    }

    fun verifyOtp(phone: String, otp: String): Boolean {
        // Always return true for test
        return true
    }

    fun getCurrentUserName(): String {
        return currentUserName
    }

    fun updateProfile(newName: String) {
        currentUserName = newName
    }

    private var notificationsEnabled: Boolean = true
    fun areNotificationsEnabled(): Boolean = notificationsEnabled
    fun setNotifications(enabled: Boolean) { notificationsEnabled = enabled }
}
