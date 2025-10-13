package com.tapnexempire.models

data class TournamentModel(
    val id: String = "",
    val title: String = "",
    val entryFee: Int = 0, // in coins
    val totalPlayers: Int = 100,
    val joinedPlayers: Int = 0,
    val durationHours: Int = 7,
    val prizePool: Int = 0,
    val startTime: Long = 0L,
    val endTime: Long = 0L,
    val category: String = "", // "100", "250", or "500"
    val isFull: Boolean = false,
    val isJoined: Boolean = false,
)
