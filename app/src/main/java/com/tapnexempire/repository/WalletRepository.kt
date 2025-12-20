package com.tapnexempire.repository

import com.tapnexempire.service.WalletService
import javax.inject.Inject

class WalletRepository @Inject constructor(
    private val walletService: WalletService
) {

    val walletState = walletService.walletState

    fun depositCoins(amount: Int) {
        walletService.addDepositCoins(amount)
    }

    fun addWinningCoins(amount: Int) {
        walletService.addWinningCoins(amount)
    }

    fun claimDailyBonus(amount: Int) {
        walletService.claimDailyBonus(amount)
    }

    fun withdrawCoins(amount: Int): Boolean {
        return walletService.withdrawCoins(amount)
    }
}
