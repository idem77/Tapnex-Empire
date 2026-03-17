package com.tapnexempire.data.model

data class TournamentModel(
    val id: String = "",
    val name: String = "",
    val entryFee: Long = 0,
    val prizePool: Long = 0,
    val maxPlayers: Long = 0,
    val joinedPlayers: Long = 0,
    val status: String = "UPCOMING",
    val createdAt: Long = System.currentTimeMillis()
)
