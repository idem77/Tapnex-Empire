package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repo: TaskRepository
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<List<TaskModel>>>(UiState.Loading)
    val state: StateFlow<UiState<List<TaskModel>>> = _state

    fun loadTasks() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val snapshot = repo.getTasks()
                val tasks = snapshot.toObjects(TaskModel::class.java)
                _state.value = UiState.Success(tasks)
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Task error")
            }
        }
    }
}
