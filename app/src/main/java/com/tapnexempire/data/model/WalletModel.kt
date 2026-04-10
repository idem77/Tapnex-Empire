package com.tapnexempire.data.model

data class WalletModel(
    val userId: String = "",

    // 💰 Real money (Tournament only)
    val depositCoins: Long = 0,

    // 🎁 Free coins (task, login)
    val bonusCoins: Long = 0,

    // 👑 Withdrawable coins
    val winningCoins: Long = 0,

    // 📅 Withdraw tracking
    val dailyWithdrawnCoins: Long = 0,
    val lastWithdrawDate: Long = 0
)
