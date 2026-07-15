package com.tapnexempire.viewmodel.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.data.model.DailyTaskModel
import com.tapnexempire.data.repository.DailyTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DailyTaskViewModel @Inject constructor(
    private val repository: DailyTaskRepository
) : ViewModel() {


    private val _tasks =
        MutableStateFlow<List<DailyTaskModel>>(
            emptyList()
        )

    val tasks: StateFlow<List<DailyTaskModel>> =
        _tasks



    fun loadTasks(){

        viewModelScope.launch {

            _tasks.value =
                repository.getDailyTasks()

        }

    }



    fun checkAnswer(
        userAnswer: String,
        correctAnswer: String
    ): Boolean {


        return userAnswer
            .trim()
            .lowercase()
            ==
            correctAnswer
                .trim()
                .lowercase()

    }


}
