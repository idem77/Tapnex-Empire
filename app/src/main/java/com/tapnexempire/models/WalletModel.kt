package com.tapnexempire.models

data class WalletModel(
    val depositBalance: Int = 0,          // Coins from tasks, bonuses, referrals, deposits
    val withdrawableBalance: Int = 0,     // Only from winnings
) {
    val totalBalance: Int
        get() = depositBalance + withdrawableBalance
}
