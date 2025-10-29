package com.tapnexempire.models

data class TransactionModel(
    val id: String = System.currentTimeMillis().toString(),
    val type: String,          // "Tournament Join", "Reward", "Task", etc.
    val amount: Int,
    val timestamp: Long = System.currentTimeMillis(),
    val isDepositCoin: Boolean = false
)
