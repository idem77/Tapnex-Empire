package com.tapnexempire.service

import com.tapnexempire.model.Wallet
import com.tapnexempire.model.Task

object WalletService {

    private var walletData = Wallet(coins = 1000, bonusCoins = 500)
    private val dailyTasks = mutableListOf(
        Task(id = 1, title = "Play 1 Game", reward = 100, isCompleted = false),
        Task(id = 2, title = "Login Daily", reward = 50, isCompleted = false),
        Task(id = 3, title = "Invite a Friend", reward = 150, isCompleted = false)
    )

    // 🪙 Get current wallet data
    fun getWalletData(): Wallet {
        return walletData
    }

    // 💰 Deposit coins (like from rewards or tasks)
    fun depositCoins(amount: Int) {
        walletData = walletData.copy(coins = walletData.coins + amount)
    }

    // 🚫 Withdraw coins (if allowed)
    fun withdrawCoins(amount: Int): Boolean {
        return if (walletData.coins >= amount) {
            walletData = walletData.copy(coins = walletData.coins - amount)
            true
        } else {
            false
        }
    }

    // 🧾 Get list of daily tasks
    fun getDailyTasks(): List<Task> {
        return dailyTasks
    }

    // ✅ Mark a task as complete
    fun completeTask(taskId: Int) {
        val task = dailyTasks.find { it.id == taskId }
        task?.let {
            if (!it.isCompleted) {
                it.isCompleted = true
                depositCoins(it.reward)
            }
        }
    }
}
