package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.models.TaskModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {

    private val _taskState = MutableStateFlow<List<TaskModel>>(emptyList())
    val taskState: StateFlow<List<TaskModel>> = _taskState

    init {
        loadTasks()
    }

    private fun loadTasks() {
        _taskState.value = listOf(
            TaskModel(1, "Login Today", 50),
            TaskModel(2, "Refer a Friend", 100),
            TaskModel(3, "Complete Daily Quiz", 150)
        )
    }

    fun completeTask(taskId: Int) {
        viewModelScope.launch {
            // TODO: update user coins, mark task complete in backend
            println("Task $taskId completed! Reward coins added.")
        }
    }
}
