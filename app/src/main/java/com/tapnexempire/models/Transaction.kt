package com.tapnexempire.models

data class Transaction(
    val type: String,       // e.g. "Deposit", "Withdraw"
    val amount: String,     // e.g. "+500", "-200"
    val date: String        // e.g. "12 Sept 2025"
)
