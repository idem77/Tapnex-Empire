package com.tapnexempire.models

data class TournamentModel(
    val id: String = "",
    val title: String = "",
    val entryCoins: Int = 0,
    val maxPlayers: Int = 0,
    val joinedPlayers: Int = 0,

    val isPaid: Boolean = false,
    val isSurveyEligible: Boolean = false,

    val empireCutPercent: Int = 70,
    val withdrawablePercent: Int = 20,

    val status: String = "UPCOMING", // UPCOMING | LIVE | COMPLETED
    val createdAt: Long = System.currentTimeMillis()
)
