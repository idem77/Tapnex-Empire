package com.tapnexempire.data.model

data class TournamentResultModel(
    val tournamentId: String = "",
    val userId: String = "",
    val rank: Int = 0,
    val winCoins: Int = 0,
    val isWithdrawable: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
