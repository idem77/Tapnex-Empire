package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.data.model.TaskModel
import com.tapnexempire.data.repository.TaskRepository
import com.tapnexempire.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repo: TaskRepository
) : ViewModel() {

    private val _tasks =
        MutableStateFlow<UiState<List<TaskModel>>>(UiState.Loading)
    val tasks: StateFlow<UiState<List<TaskModel>>> = _tasks

    fun loadTasks() {
        viewModelScope.launch {
            _tasks.value = UiState.Loading
            try {
                val list = repo.getTasks()
                _tasks.value = UiState.Success(list)
            } catch (e: Exception) {
                _tasks.value = UiState.Error(e.message ?: "Error")
            }
        }
    }
}
