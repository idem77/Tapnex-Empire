package com.tapnexempire.service

import com.tapnexempire.models.Task

object TaskService {
    private val tasks = listOf(
        Task("1", "Daily Login", "Login to app", 10),
        Task("2", "Invite Friends", "Invite 3 friends", 30)
    )

    fun getTasks() = tasks
    fun completeTask(taskId: String) { /* just demo */ }
}
