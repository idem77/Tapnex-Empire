package com.tapnexempire.data.model

data class WithdrawHistoryModel(

    val id: String = "",

    val userId: String = "",

    val amountCoins: Long = 0,

    val amountRupees: Long = 0,

    val rewardType: String = "",

    val redeemCode: String = "",

    val status: String = "",

    val createdAt: Long = 0,

    val approvedAt: Long = 0
)
