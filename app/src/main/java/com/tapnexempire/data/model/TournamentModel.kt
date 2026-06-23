package com.tapnexempire.data.model

data class TournamentModel(
    val id: String = "",
    val title: String = "",
    val entryFee: Long = 0,
    val prizePool: Long = 0,
    val maxPlayers: Long = 0,
    val joinedPlayers: Long = 0,
    val status: String = "UPCOMING",
    val durationMinutes: Long = 0L,
    val startTime: Long = 0L,
    val endTime: Long = 0L,
    val createdAt: Long = System.currentTimeMillis()
)
