package com.tapnexempire.ui.wallet

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class WalletState(
    val depositCoins: Int = 0,
    val winningCoins: Int = 0
) {
    val totalCoins: Int
        get() = depositCoins + winningCoins
}

class WalletViewModel : ViewModel() {

    private val _walletState = MutableStateFlow(WalletState())
    val walletState: StateFlow<WalletState> = _walletState

    // 🪙 Add deposit coins (task, daily reward, referral, etc.)
    fun addDepositCoins(amount: Int) {
        _walletState.update { it.copy(depositCoins = it.depositCoins + amount) }
    }

    // 🏆 Add winning coins (from tournament rewards)
    fun addWinningCoins(amount: Int) {
        _walletState.update { it.copy(winningCoins = it.winningCoins + amount) }
    }

    // 💸 Withdraw only from winning balance
    fun withdrawCoins(amount: Int): Boolean {
        return if (_walletState.value.winningCoins >= amount) {
            _walletState.update { it.copy(winningCoins = it.winningCoins - amount) }
            true
        } else false
    }

    // 🎮 Deduct coins when joining tournament (deposit used first)
    fun deductCoins(amount: Int): Boolean {
        val current = _walletState.value
        return if (current.totalCoins >= amount) {
            var remaining = amount
            var deposit = current.depositCoins
            var winning = current.winningCoins

            if (deposit >= remaining) {
                deposit -= remaining
                remaining = 0
            } else {
                remaining -= deposit
                deposit = 0
                winning -= remaining
            }

            _walletState.update { it.copy(depositCoins = deposit, winningCoins = winning) }
            true
        } else false
    }
}
