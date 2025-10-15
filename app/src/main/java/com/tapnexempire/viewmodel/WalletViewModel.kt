package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.tapnexempire.models.Transaction

class WalletViewModel : ViewModel() {

    var balance by mutableStateOf(0)
        private set

    var transactions = mutableListOf<Transaction>()
        private set

    // 🔹 Add coins (reward, task, etc.)
    fun addCoins(amount: Int, type: String, isDeposit: Boolean = false) {
        balance += amount
        transactions.add(Transaction(type = type, amount = amount, isDepositCoin = isDeposit))
    }

    // 🔹 Deduct coins (join tournament)
    fun deductCoins(amount: Int, type: String): Boolean {
        return if (balance >= amount) {
            balance -= amount
            transactions.add(Transaction(type = type, amount = -amount))
            true
        } else false
    }

    // 🔹 Daily reward
    fun dailyReward() {
        addCoins(500, "Daily Login Reward")
    }

    // 🔹 Prevent withdraw for deposit coins
    fun withdrawCoins(): Boolean {
        val withdrawable = transactions.none { it.isDepositCoin }
        return withdrawable
    }
}
