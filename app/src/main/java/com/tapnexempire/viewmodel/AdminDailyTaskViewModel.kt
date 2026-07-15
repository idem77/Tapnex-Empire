package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.data.model.DailyTaskModel
import com.tapnexempire.data.repository.AdminDailyTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AdminDailyTaskViewModel @Inject constructor(
    private val repository: AdminDailyTaskRepository
) : ViewModel() {

    private val _isLoading =
        MutableStateFlow(false)

    val isLoading: StateFlow<Boolean> =
        _isLoading

    private val _message =
        MutableStateFlow("")

    val message: StateFlow<String> =
        _message

    private val _taskList =
        MutableStateFlow<List<DailyTaskModel>>(
            emptyList()
        )

    val taskList: StateFlow<List<DailyTaskModel>> =
        _taskList



    fun loadTasks() {

        viewModelScope.launch {

            _taskList.value =
                repository.getAllTasks()

        }

    }



    fun createTask(

        animeName: String,

        title: String,

        description: String,

        question: String,

        correctAnswer: String,

        hint: String,

        youtubeLink: String,

        rewardCoins: Int

    ) {

        if (
            animeName.isBlank() ||
            title.isBlank() ||
            question.isBlank() ||
            correctAnswer.isBlank()
        ) {

            _message.value =
                "Please fill required fields."

            return

        }



        viewModelScope.launch {

            _isLoading.value = true

            val task = DailyTaskModel(

                id = UUID.randomUUID().toString(),

                animeName = animeName,

                title = title,

                description = description,

                question = question,

                correctAnswer = correctAnswer,

                hint = hint,

                youtubeLink = youtubeLink,

                rewardCoins = rewardCoins,

                isActive = true

            )



            val result =
                repository.createTask(task)



            result.onSuccess {

                _message.value =
                    "Task Published Successfully."

                loadTasks()

            }



            result.onFailure {

                _message.value =
                    it.message
                        ?: "Upload Failed"

            }



            _isLoading.value = false

        }

    }



    fun deleteTask(id: String) {

        viewModelScope.launch {

            repository.deleteTask(id)

            loadTasks()

        }

    }

}
