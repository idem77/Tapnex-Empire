package com.tapnexempire.service

import com.tapnexempire.models.TransactionModel
import com.tapnexempire.models.WalletModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WalletService {

    private val _walletState = MutableStateFlow(
        WalletModel(
            totalCoins = 0,
            depositCoins = 0,
            withdrawableCoins = 0,
            transactions = emptyList()
        )
    )

    val walletState: StateFlow<WalletModel> = _walletState

    fun addDepositCoins(amount: Int) {
        val current = _walletState.value
        val transaction = TransactionModel(
            title = "Deposit",
            coins = amount,
            isWithdrawable = false
        )

        _walletState.value = current.copy(
            totalCoins = current.totalCoins + amount,
            depositCoins = current.depositCoins + amount,
            transactions = current.transactions + transaction
        )
    }

    fun addWinningCoins(amount: Int) {
        val current = _walletState.value
        val transaction = TransactionModel(
            title = "Tournament Win",
            coins = amount,
            isWithdrawable = true
        )

        _walletState.value = current.copy(
            totalCoins = current.totalCoins + amount,
            withdrawableCoins = current.withdrawableCoins + amount,
            transactions = current.transactions + transaction
        )
    }

    fun claimDailyBonus(amount: Int) {
        val current = _walletState.value
        val transaction = TransactionModel(
            title = "Daily Bonus",
            coins = amount,
            isWithdrawable = false
        )

        _walletState.value = current.copy(
            totalCoins = current.totalCoins + amount,
            depositCoins = current.depositCoins + amount,
            transactions = current.transactions + transaction
        )
    }

    fun withdrawCoins(amount: Int): Boolean {
        val current = _walletState.value
        if (current.withdrawableCoins < amount) return false

        val transaction = TransactionModel(
            title = "Withdrawal",
            coins = -amount,
            isWithdrawable = true
        )

        _walletState.value = current.copy(
            totalCoins = current.totalCoins - amount,
            withdrawableCoins = current.withdrawableCoins - amount,
            transactions = current.transactions + transaction
        )
        return true
    }
}
