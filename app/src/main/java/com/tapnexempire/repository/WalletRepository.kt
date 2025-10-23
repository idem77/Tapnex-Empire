package com.tapnexempire.repository

import com.tapnexempire.models.TaskModel
import kotlinx.coroutines.delay
import javax.inject.Inject

data class WalletData(
    val deposit: Int,
    val withdrawable: Int,
    val referralRewards: Int,
    val totalCoins: Int,
    val transactions: List<String>
)

class WalletRepository @Inject constructor() {

    private var depositCoins = 5000   // Coins user added or got via bonuses
    private var withdrawableCoins = 0 // User cannot withdraw these in Tapnex
    private var referralRewards = 250
    private var transactions = mutableListOf<String>()

    suspend fun getWalletData(): WalletData {
        delay(300) // simulate API delay
        val total = depositCoins + withdrawableCoins + referralRewards
        return WalletData(
            deposit = depositCoins,
            withdrawable = withdrawableCoins,
            referralRewards = referralRewards,
            totalCoins = total,
            transactions = transactions.toList()
        )
    }

    suspend fun depositCoins(amount: Int) {
        delay(200)
        depositCoins += amount
        transactions.add("Deposited $amount coins")
    }

    suspend fun withdrawCoins(amount: Int) {
        delay(200)
        if (withdrawableCoins >= amount) {
            withdrawableCoins -= amount
            transactions.add("Withdrawn $amount coins")
        } else {
            transactions.add("Withdraw failed: Not enough withdrawable balance")
        }
    }

    suspend fun getDailyTasks(): List<TaskModel> {
        delay(200)
        return listOf(
            TaskModel("1", "Play 3 tournaments", 300, false),
            TaskModel("2", "Refer a friend", 200, false),
            TaskModel("3", "Login daily", 100, true)
        )
    }

    suspend fun completeTask(taskId: String) {
        delay(200)
        when (taskId) {
            "1" -> {
                depositCoins += 300
                transactions.add("Completed: Play 3 tournaments (+300 coins)")
            }
            "2" -> {
                referralRewards += 200
                transactions.add("Completed: Refer a friend (+200 coins)")
            }
            "3" -> {
                depositCoins += 100
                transactions.add("Completed: Daily login (+100 coins)")
            }
        }
    }
}
