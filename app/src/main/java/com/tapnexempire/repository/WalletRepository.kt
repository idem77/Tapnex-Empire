package com.tapnexempire.repository

import com.tapnexempire.models.TransactionModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WalletRepository @Inject constructor() {

    private val _transactions = MutableStateFlow<List<TransactionModel>>(emptyList())
    val transactions = _transactions.asStateFlow()

    private var totalCoins = 0

    // ✅ Add coins (rewards, tasks, etc.)
    fun addCoins(amount: Int, type: String = "Reward", isDepositCoin: Boolean = false) {
        totalCoins += amount
        addTransaction(
            TransactionModel(
                id = System.currentTimeMillis().toString(),
                type = type,
                amount = amount,
                isDepositCoin = isDepositCoin
            )
        )
    }

    // ✅ Deduct coins for joins, etc.
    fun deductCoins(amount: Int, type: String = "Tournament Join") {
        if (totalCoins >= amount) {
            totalCoins -= amount
            addTransaction(
                TransactionModel(
                    id = System.currentTimeMillis().toString(),
                    type = type,
                    amount = -amount,
                    isDepositCoin = false
                )
            )
        }
    }

    private fun addTransaction(transaction: TransactionModel) {
        _transactions.value = _transactions.value + transaction
    }

    fun getBalance(): Int = totalCoins
}
