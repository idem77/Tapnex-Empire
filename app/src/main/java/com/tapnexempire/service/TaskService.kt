package com.tapnexempire.service

import com.tapnexempire.models.Task

object TaskService {
    private val tasks = mutableListOf(
        Task("Complete Profile", false),
        Task("Play First Game", false)
    )

    fun getTasks(): List<Task> = tasks

    fun completeTask(taskTitle: String) {
        tasks.find { it.title == taskTitle }?.isCompleted = true
    }
}
