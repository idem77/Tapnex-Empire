package com.tapnexempire.service

import com.tapnexempire.models.UserModel
import kotlinx.coroutines.delay

class AuthService {

    suspend fun sendOtp(phone: String): Boolean {
        delay(300)
        return true
    }

    suspend fun verifyOtp(phone: String, otp: String): UserModel {
        delay(300)
        return UserModel(
            uid = "UID_${phone.takeLast(4)}",
            phone = phone,
            isNewUser = true
        )
    }
}
