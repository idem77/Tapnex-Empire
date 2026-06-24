package com.tapnexempire.data.model

data class LeaderboardPlayer(

    val userId: String = "",

    val username: String = "",

    val empirePoints: Long = 0L,

    val tournamentsPlayed: Long = 0L,

    val totalWonCoins: Long = 0L
)
