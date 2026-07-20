package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.data.model.DailyTaskModel
import com.tapnexempire.data.repository.WalletRepository
import com.tapnexempire.data.repository.TransactionRepository
import com.tapnexempire.data.model.TransactionModel
import java.util.UUID
import com.tapnexempire.data.repository.DailyTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DailyTaskViewModel @Inject constructor(
    private val repository: DailyTaskRepository,
    private val walletRepository: WalletRepository,
    private val transactionRepository: TransactionRepository
) : ViewModel() {
    

    private val _tasks =
        MutableStateFlow<List<DailyTaskModel>>(
            emptyList()
        )

    private val _message =
    MutableStateFlow("")

    private val _isLoading =
    MutableStateFlow(false)

val isLoading: StateFlow<Boolean> =
    _isLoading
    
val message: StateFlow<String> =
    _message

    val tasks: StateFlow<List<DailyTaskModel>> =
        _tasks



    fun loadTasks() {

    viewModelScope.launch {

        try {

            _isLoading.value = true

            _tasks.value =
                repository.getDailyTasks()

        } catch (e: Exception) {

            _message.value =
                e.message ?: "Unknown Error"

        } finally {

            _isLoading.value = false

        }

    fun checkAnswer(
    userAnswer: String,
    correctAnswer: String
): Boolean {

    val user = userAnswer
        .trim()
        .replace("\\s+".toRegex(), " ")
        .lowercase()

    val answer = correctAnswer
        .trim()
        .replace("\\s+".toRegex(), " ")
        .lowercase()

    return user == answer
    }
}

fun submitAnswer(
    userId: String,
    task: DailyTaskModel,
    answer: String
) {

    if (!checkAnswer(answer, task.correctAnswer)) {
        _message.value = "❌ Wrong Answer"
        return
    }


    viewModelScope.launch {


        val reward =
            task.rewardCoins.toLong()


        val walletResult =
            walletRepository.addBonusCoins(
                userId,
                reward
            )


        if(walletResult.isSuccess) {


            val transaction =
                TransactionModel(

                    id = UUID.randomUUID()
                        .toString(),

                    userId = userId,

                    type = "BONUS",

                    amount = 0,

                    coins = reward.toInt(),

                    status = "SUCCESS",

                    description =
                    "Daily Anime Quiz Reward",

                    createdAt =
                    System.currentTimeMillis()

                )


            transactionRepository
                .addTransaction(transaction)


            _message.value =
                "✅ Correct +${task.rewardCoins} Coins"

        } else {

            _message.value =
                "❌ Reward Failed"

        }

    }

   }
  }
}
