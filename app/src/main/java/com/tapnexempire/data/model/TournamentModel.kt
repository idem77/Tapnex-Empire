package com.tapnexempire.models

data class TournamentModel(
    val id: String = "",
    val name: String = "", // 🔥 FIXED
    val entryFee: Long = 0, // 🔥 FIXED
    val prizePool: Long = 0, // 🔥 ADDED
    val maxPlayers: Long = 0,
    val joinedPlayers: Long = 0,

    val isPaid: Boolean = false,
    val isSurveyEligible: Boolean = false,

    val empireCutPercent: Int = 70,
    val withdrawablePercent: Int = 20,

    val status: String = "UPCOMING",
    val createdAt: Long = System.currentTimeMillis()
)
