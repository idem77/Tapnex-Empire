package com.tapnexempire.data.model

enum class TransactionType {
    DEPOSIT,
    WIN,
    WITHDRAW,
    BONUS,
    ENTRY_FEE
}

data class TransactionModel(

    val id: String = "",

    val userId: String = "",

    val type: String = "",

    val amount: Int = 0,

    val coins: Int = 0,

    val status: String = "",

    val description: String = "",

    val createdAt: Long = 0
)
