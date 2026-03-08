package com.tapnexempire.model

data class TaskModel(
    val id: String = "",           // 🆔 Unique ID for each task
    val title: String = "",        // 📋 Task title (e.g., "Play 1 Match")
    val description: String = "",  // 📝 Optional description for clarity
    val reward: Int = 0,           // 💰 Reward coins for completing this task
    val isCompleted: Boolean = false  // ✅ To mark completion status
)
