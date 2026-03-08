package com.tapnexempire.model

enum class TransactionType {
    DEPOSIT,
    WIN,
    WITHDRAW,
    BONUS,
    ENTRY_FEE
}

data class TransactionModel(
    val id: String,
    val type: TransactionType,
    val amount: Int,
    val timestamp: Long,
    val description: String
)
