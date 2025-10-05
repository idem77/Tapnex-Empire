package com.tapnexempire.service

import com.tapnexempire.models.Task

object TaskService {
    private val tasks = listOf(
        Task("1", "Complete Profile", "Fill your profile to get coins", 50),
        Task("2", "Daily Check-in", "Log in daily to earn rewards", 10)
    )

    fun getTasks(): List<Task> = tasks
}
