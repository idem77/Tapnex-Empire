package com.tapnexempire.models

data class WalletModel(
    val userId: String = "",
    
    val depositCoins: Int = 0,        // 💰 Bonus + Task + Referral + Daily Reward
    val withdrawableCoins: Int = 0,   // 🏆 Only winnings
    val referralRewards: Int = 0,     // 🎁 Referral-only tracker
    
    val totalCoins: Int = 0           // deposit + withdrawable
)
