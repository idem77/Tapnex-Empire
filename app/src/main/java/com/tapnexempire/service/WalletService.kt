package com.tapnexempire.service

import com.tapnexempire.models.Wallet

object WalletService {

    // 🧠 In-memory store (later you can connect to Firebase / Room DB)
    private val wallets = mutableMapOf<String, Wallet>()

    // 🪙 Initialize wallet for new user
    fun createWallet(userId: String) {
        if (wallets[userId] == null) {
            wallets[userId] = Wallet(
                userId = userId,
                depositCoins = 0.0,
                withdrawableCoins = 0.0
            )
        }
    }

    // 💸 Get balances
    fun getDepositBalance(userId: String): Double = wallets[userId]?.depositCoins ?: 0.0
    fun getWithdrawableBalance(userId: String): Double = wallets[userId]?.withdrawableCoins ?: 0.0
    fun getTotalBalance(userId: String): Double = getDepositBalance(userId) + getWithdrawableBalance(userId)

    // ➕ Add coins
    fun addDeposit(userId: String, amount: Double) {
        val wallet = wallets[userId] ?: createWalletAndReturn(userId)
        wallet.depositCoins += amount
    }

    fun addWithdrawable(userId: String, amount: Double) {
        val wallet = wallets[userId] ?: createWalletAndReturn(userId)
        wallet.withdrawableCoins += amount
    }

    // ➖ Deduct coins
    fun deductDeposit(userId: String, amount: Double) {
        val wallet = wallets[userId] ?: createWalletAndReturn(userId)
        wallet.depositCoins = (wallet.depositCoins - amount).coerceAtLeast(0.0)
    }

    fun deductWithdrawable(userId: String, amount: Double) {
        val wallet = wallets[userId] ?: createWalletAndReturn(userId)
        wallet.withdrawableCoins = (wallet.withdrawableCoins - amount).coerceAtLeast(0.0)
    }

    // 🧾 Helper to ensure wallet exists
    private fun createWalletAndReturn(userId: String): Wallet {
        createWallet(userId)
        return wallets[userId]!!
    }

    // 🪄 For testing/debug (you can remove later)
    fun printWallet(userId: String) {
        val w = wallets[userId]
        if (w != null) {
            println("Wallet of $userId → Deposit: ${w.depositCoins}, Withdrawable: ${w.withdrawableCoins}, Total: ${getTotalBalance(userId)}")
        } else {
            println("Wallet not found for user $userId")
        }
    }
}
