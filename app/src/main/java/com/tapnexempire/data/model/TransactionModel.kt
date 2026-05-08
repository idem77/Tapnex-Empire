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

    val type: TransactionType = TransactionType.BONUS,

    val amount: Long = 0,

    val coins: Long = 0,

    val status: String = "SUCCESS",

    val description: String = "",

    val createdAt: Long = System.currentTimeMillis()
)
