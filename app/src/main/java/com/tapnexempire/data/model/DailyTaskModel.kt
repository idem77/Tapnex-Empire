package com.tapnexempire.data.model

data class DailyTaskModel(

    val id: String = "",

    val animeName: String = "",

    val title: String = "",

    val description: String = "",

    val question: String = "",

    val correctAnswer: String = "",

    val hint: String = "",

    val youtubeLink: String = "",

    val rewardCoins: Int = 0,

    val isActive: Boolean = true
)
