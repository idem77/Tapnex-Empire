package com.tapnexempire.service

import com.tapnexempire.models.Transaction

object WalletService {
    private var coinBalance: Int = 1000
    private var depositBalance: Int = 500
    private var withdrawableBalance: Int = 300
    private var referralRewards: Int = 50
    private val transactions: MutableList<Transaction> = mutableListOf()

    fun getCoinBalance(): Int = coinBalance
    fun getDepositBalance(): Int = depositBalance
    fun getWithdrawableBalance(): Int = withdrawableBalance
    fun getReferralRewards(): Int = referralRewards

    fun deposit(amount: Int) {
        depositBalance += amount
        transactions.add(Transaction("Deposit", amount.toString(), ""))
    }

    fun withdraw(amount: Int) {
        withdrawableBalance -= amount
        transactions.add(Transaction("Withdraw", amount.toString(), ""))
    }

    fun getTransactions(): List<Transaction> = transactions
}
