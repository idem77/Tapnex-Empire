package com.tapnexempire.service

import com.tapnexempire.models.Transaction

object WalletService {
    private var depositBalance = 100
    private var withdrawableBalance = 50
    private var referralRewards = 10

    private val transactions = mutableListOf<Transaction>()

    fun getDepositBalance() = depositBalance
    fun getWithdrawableBalance() = withdrawableBalance
    fun getReferralRewards() = referralRewards

    fun deposit(amount: Int) {
        depositBalance += amount
        transactions.add(Transaction("1", "Today", amount, "Deposit"))
    }

    fun withdraw(amount: Int) {
        if (withdrawableBalance >= amount) {
            withdrawableBalance -= amount
            transactions.add(Transaction("2", "Today", amount, "Withdraw"))
        }
    }

    fun getTransactions() = transactions
    fun getCoinBalance() = depositBalance + withdrawableBalance + referralRewards
}
