package com.tapnexempire.service

import com.tapnexempire.models.Transaction

object WalletService {
    private var depositBalance = 100
    private var withdrawableBalance = 50
    private val transactions = mutableListOf<Transaction>()

    fun getBalances(): Pair<Int, Int> {
        return Pair(depositBalance, withdrawableBalance)
    }

    fun deposit(amount: Int) {
        depositBalance += amount
        transactions.add(Transaction("Deposit", amount))
    }

    fun withdraw(amount: Int): Boolean {
        return if (withdrawableBalance >= amount) {
            withdrawableBalance -= amount
            transactions.add(Transaction("Withdraw", amount))
            true
        } else {
            false
        }
    }

    fun getTransactions(): List<Transaction> = transactions
}
