package com.tapnexempire.models

data class Transaction(
    val id: String,
    val date: String,
    val amount: Int,
    val type: String // Deposit / Withdraw
)
