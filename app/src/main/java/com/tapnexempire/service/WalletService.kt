package com.tapnexempire.service

import com.tapnexempire.models.Transaction

object WalletService {
    private var coins: Int = 500

    fun getCoinBalance(): Int = coins

    fun getTransactions(): List<Transaction> {
        return listOf(
            Transaction("1", 50.0, "credit", "2025-10-01"),
            Transaction("2", 20.0, "debit", "2025-10-02")
        )
    }

    fun addCoins(amount: Int) {
        coins += amount
    }

    fun deductCoins(amount: Int) {
        coins -= amount
    }
}
