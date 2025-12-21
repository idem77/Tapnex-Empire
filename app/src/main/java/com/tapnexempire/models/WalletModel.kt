package com.tapnexempire.models

data class WalletModel(
    val depositCoins: Int = 0,
    val winningCoins: Int = 0,
    val transactions: List<TransactionModel> = emptyList()
) {
    val totalCoins: Int
        get() = depositCoins + winningCoins
}
