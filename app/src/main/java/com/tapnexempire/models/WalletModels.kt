package com.tapnexempire.model

data class Wallet(
    val userId: String = "",
    val coins: Int = 0,
    val lastUpdated: Long = System.currentTimeMillis()
)

data class Task(
    val id: String = "",
    val title: String = "",
    val reward: Int = 0,
    val isCompleted: Boolean = false
)
