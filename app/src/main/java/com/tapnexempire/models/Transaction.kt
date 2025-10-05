package com.tapnexempire.models

data class Transaction(
    val id: String,
    val amount: Double,
    val type: String, // "credit" or "debit"
    val date: String
)
