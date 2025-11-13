package com.tapnexempire.models

data class Wallet(
    val depositBalance: Int = 0,
    val withdrawableBalance: Int = 0,
    val referralRewards: Int = 0,
    val totalCoins: Int = 0
)

data class Task(
    val id: String,
    val title: String,
    val rewardCoins: Int,
    val isCompleted: Boolean = false
)
