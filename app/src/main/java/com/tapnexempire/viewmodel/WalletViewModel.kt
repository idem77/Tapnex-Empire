package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import com.tapnexempire.models.TransactionModel
import com.tapnexempire.models.WalletModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WalletViewModel : ViewModel() {

    private val _walletState = MutableStateFlow(
        WalletModel(
            totalCoins = 500,
            withdrawableCoins = 0,
            depositCoins = 500,
            transactions = emptyList()
        )
    )

    val walletState: StateFlow<WalletModel> = _walletState

    fun addDepositCoins(coins: Int) {
        updateWallet(coins, isWithdrawable = false, "Deposit")
    }

    fun addWinningCoins(coins: Int) {
        updateWallet(coins, isWithdrawable = true, "Winning")
    }

    private fun updateWallet(coins: Int, isWithdrawable: Boolean, type: String) {
        val current = _walletState.value
        val updatedTransaction = TransactionModel(
            type = type,
            amount = coins
        )

        _walletState.value = current.copy(
            totalCoins = current.totalCoins + coins,
            withdrawableCoins = if (isWithdrawable)
                current.withdrawableCoins + coins else current.withdrawableCoins,
            depositCoins = if (!isWithdrawable)
                current.depositCoins + coins else current.depositCoins,
            transactions = listOf(updatedTransaction) + current.transactions
        )
    }
}
