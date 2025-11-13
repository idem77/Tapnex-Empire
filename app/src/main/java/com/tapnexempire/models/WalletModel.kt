package com.tapnexempire.models

data class WalletModel(
    val userId: String = "",
    val depositBalance: Int = 0,
    val withdrawableBalance: Int = 0
) {
    val totalBalance: Int
        get() = depositBalance + withdrawableBalance
}
