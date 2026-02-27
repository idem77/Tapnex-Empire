package com.tapnexempire.models

data class WalletModel(
    val userId: String = "",
    val depositCoins: Long = 0,
    val bonusCoins: Long = 0,
    val withdrawableCoins: Long = 0,
    val lockedCoins: Long = 0,
    val totalCoins: Long = 0,
    val dailyWithdrawnCoins: Long = 0,
    val lastWithdrawDate: Long = 0
)
