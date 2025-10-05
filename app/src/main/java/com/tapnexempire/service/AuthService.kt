package com.tapnexempire.service

object AuthService {
    private var currentUserName = "Demo User"
    private var notificationsEnabled = true

    fun login(phone: String) = true
    fun signup(phone: String) = true
    fun verifyOtp(phone: String, otp: String) = otp == "1234"

    fun getCurrentUserName() = currentUserName
    fun updateProfile(newName: String) { currentUserName = newName }

    fun areNotificationsEnabled() = notificationsEnabled
    fun setNotifications(enabled: Boolean) { notificationsEnabled = enabled }
}
