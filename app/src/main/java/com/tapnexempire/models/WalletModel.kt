package com.tapnexempire.models

data class WalletModel(
    val userId: String = "",
    val depositBalance: Int = 0,       // 💰 Coins from bonuses, referrals, daily rewards, tasks
    val withdrawableBalance: Int = 0,  // 🏆 Coins from winnings (can withdraw)
    val referralRewards: Int = 0,      // 🎁 Separate tracker for referral rewards
    val totalCoins: Int = 0            // 🔢 Combined total (deposit + withdrawable)
)
