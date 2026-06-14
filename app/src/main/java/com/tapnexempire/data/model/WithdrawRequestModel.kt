package com.tapnexempire.data.model

data class WithdrawRequestModel(

    val id: String = "",

    val userId: String = "",

    val amountCoins: Long = 0,

    val amountRupees: Long = 0,

    val redeemCode: String = "",

    val status: String = "pending",

    val createdAt: Long = 0L,

    val approvedAt: Long = 0L,

    val approvedBy: String = ""
)
