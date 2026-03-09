package com.tapnexempire.data.model

data class SurveyLeaderboardModel(
    val userId: String = "",
    val paidTournamentsPlayed: Int = 0,
    val lastUpdated: Long = System.currentTimeMillis()
)
