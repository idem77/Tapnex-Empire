package com.tapnexempire.models

data class TournamentModel(
    val id: String,
    val name: String,
    val entryFee: Int,
    val prizePool: Int,
    val maxPlayers: Int,
    val joinedPlayers: Int = 0,
    val isJoined: Boolean = false,
    val isCompleted: Boolean = false
)
