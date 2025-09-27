package com.tapnexempire.service

import com.tapnexempire.models.Task

object TaskService {
    private val tasks = listOf(
        Task("1", "Invite a friend", 10, false),
        Task("2", "Play 1 game", 5, false)
    )

    fun getTasks(): List<Task> = tasks

    fun completeTask(taskId: String): Boolean {
        // Later: call API
        return true
    }
}
