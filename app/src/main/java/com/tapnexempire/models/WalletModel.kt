package com.tapnexempire.models

data class WalletModel(
    val userId: String = "",
    val depositCoins: Int = 0,        // User ke real deposit coins
    val bonusCoins: Int = 0,          // Ads / tasks se aaye
    val withdrawableCoins: Int = 0,   // Withdraw allowed
    val lockedCoins: Int = 0,         // Free / survey winnings
    val totalEarnings: Int = 0,       // Lifetime coins earned
    val updatedAt: Long = System.currentTimeMillis()
)
