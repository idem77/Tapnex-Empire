package com.tapnexempire.service

import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.models.WalletModel
import com.tapnexempire.models.TaskModel
import kotlinx.coroutines.tasks.await

class WalletService {

    private val db = FirebaseFirestore.getInstance()

    // 🪙 Get user wallet from Firestore
    suspend fun getWalletData(userId: String): WalletModel {
        val doc = db.collection("wallets").document(userId).get().await()
        return doc.toObject(WalletModel::class.java) ?: WalletModel(userId = userId)
    }

    // 💰 Add coins to deposit balance (from bonuses, referrals, tasks)
    suspend fun addDepositCoins(userId: String, amount: Int): WalletModel {
        val wallet = getWalletData(userId)
        val updatedWallet = wallet.copy(
            depositBalance = wallet.depositBalance + amount,
            totalCoins = wallet.totalCoins + amount
        )
        db.collection("wallets").document(userId).set(updatedWallet).await()
        return updatedWallet
    }

    // 🏆 Add winning coins (goes to withdrawable balance)
    suspend fun addWinningCoins(userId: String, amount: Int): WalletModel {
        val wallet = getWalletData(userId)
        val updatedWallet = wallet.copy(
            withdrawableBalance = wallet.withdrawableBalance + amount,
            totalCoins = wallet.totalCoins + amount
        )
        db.collection("wallets").document(userId).set(updatedWallet).await()
        return updatedWallet
    }

    // ❌ Withdraw coins only from withdrawable balance
    suspend fun withdrawCoins(userId: String, amount: Int): WalletModel {
        val wallet = getWalletData(userId)
        val newWithdrawable = (wallet.withdrawableBalance - amount).coerceAtLeast(0)
        val difference = wallet.withdrawableBalance - newWithdrawable
        val newTotal = (wallet.totalCoins - difference).coerceAtLeast(0)

        val updatedWallet = wallet.copy(
            withdrawableBalance = newWithdrawable,
            totalCoins = newTotal
        )

        db.collection("wallets").document(userId).set(updatedWallet).await()
        return updatedWallet
    }

    // 🧩 Get mock daily tasks
    suspend fun getDailyTasks(): List<TaskModel> {
        return listOf(
            TaskModel(id = "1", title = "Play 1 Match", reward = 100),
            TaskModel(id = "2", title = "Login Today", reward = 50),
            TaskModel(id = "3", title = "Refer a Friend", reward = 200)
        )
    }

    // ✅ Complete a task (adds deposit balance)
    suspend fun completeTask(userId: String, task: TaskModel): WalletModel {
        return addDepositCoins(userId, task.reward)
    }
}
