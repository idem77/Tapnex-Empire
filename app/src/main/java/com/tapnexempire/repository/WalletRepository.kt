package com.tapnexempire.repository

import com.tapnexempire.models.TaskModel
import com.tapnexempire.models.TransactionModel
import kotlinx.coroutines.delay
import javax.inject.Inject

data class WalletData(
    val deposit: Int,
    val withdrawable: Int,
    val referralRewards: Int,
    val totalCoins: Int,
    val transactions: List<TransactionModel>
)

class WalletRepository @Inject constructor() {

    private var depositCoins = 5000   // Coins user added or got via bonuses
    private var withdrawableCoins = 0 // User cannot withdraw these in Tapnex
    private var referralRewards = 250
    private var transactions = mutableListOf<TransactionModel>()

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
        transactions.add(
            TransactionModel(
                type = "Deposit",
                amount = amount,
                isDepositCoin = true
            )
        )
    }

    suspend fun withdrawCoins(amount: Int) {
        delay(200)
        if (withdrawableCoins >= amount) {
            withdrawableCoins -= amount
            transactions.add(
                TransactionModel(
                    type = "Withdraw",
                    amount = amount,
                    isDepositCoin = false
                )
            )
        } else {
            transactions.add(
                TransactionModel(
                    type = "Withdraw Failed",
                    amount = amount,
                    isDepositCoin = false
                )
            )
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
                transactions.add(
                    TransactionModel(
                        type = "Task Reward",
                        amount = 300,
                        isDepositCoin = true
                    )
                )
            }
            "2" -> {
                referralRewards += 200
                transactions.add(
                    TransactionModel(
                        type = "Referral Reward",
                        amount = 200,
                        isDepositCoin = true
                    )
                )
            }
            "3" -> {
                depositCoins += 100
                transactions.add(
                    TransactionModel(
                        type = "Daily Login Reward",
                        amount = 100,
                        isDepositCoin = true
                    )
                )
            }
        }
    }
}
