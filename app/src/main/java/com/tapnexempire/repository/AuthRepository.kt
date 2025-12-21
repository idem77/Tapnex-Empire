package com.tapnexempire.repository

import com.tapnexempire.models.UserModel
import com.tapnexempire.service.AuthService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authService: AuthService,
    private val walletRepository: WalletRepository
) {

    var currentUser: UserModel? = null
        private set

    suspend fun sendOtp(phone: String): Boolean {
        return authService.sendOtp(phone)
    }

    suspend fun verifyOtp(phone: String, otp: String): Boolean {
        val user = authService.verifyOtp(phone, otp)
        currentUser = user

        if (user.isNewUser) {
            walletRepository.addDepositCoins(500) // 🎁 Welcome bonus
        }
        return true
    }

    fun isLoggedIn(): Boolean = currentUser != null

    fun logout() {
        currentUser = null
    }
}
