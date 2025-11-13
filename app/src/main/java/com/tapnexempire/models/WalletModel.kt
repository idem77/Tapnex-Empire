package com.tapnexempire.model

data class WalletModel(
    val userId: String = "",
    val coins: Int = 0,
    val lastUpdated: Long = System.currentTimeMillis()
)
