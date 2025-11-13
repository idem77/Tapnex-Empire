package com.tapnexempire.service

import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.models.WalletModel
import com.tapnexempire.models.TaskModel
import kotlinx.coroutines.tasks.await

class WalletService {

    private val db = FirebaseFirestore.getInstance()

    // 🪙 Fetch user wallet
    suspend fun getWalletData(userId: String): WalletModel {
        val doc = db.collection("wallets").document(userId).get().await()
        return doc.toObject(WalletModel::class.java) ?: WalletModel(userId = userId)
    }

    // 💰 Add deposit coins (from task, referral, bonus)
    suspend fun addDepositCoins(userId: String, amount: Int): WalletModel {
        val wallet = getWalletData(userId)
        val updatedWallet = wallet.copy(
            depositBalance = wallet.depositBalance + amount
        )
        db.collection("wallets").document(userId).set(updatedWallet).await()
        return updatedWallet
    }

    // 🏆 Add withdrawable coins (from winnings)
    suspend fun addWinningCoins(userId: String, amount: Int): WalletModel {
        val wallet = getWalletData(userId)
        val updatedWallet = wallet.copy(
            withdrawableBalance = wallet.withdrawableBalance + amount
        )
        db.collection("wallets").document(userId).set(updatedWallet).await()
        return updatedWallet
    }

    // ❌ Withdraw coins (only from withdrawable balance)
    suspend fun withdrawCoins(userId: String, amount: Int): WalletModel {
        val wallet = getWalletData(userId)
        val newWithdrawable = (wallet.withdrawableBalance - amount).coerceAtLeast(0)
        val updatedWallet = wallet.copy(withdrawableBalance = newWithdrawable)
        db.collection("wallets").document(userId).set(updatedWallet).await()
        return updatedWallet
    }

    // 🏆 Daily Tasks (mock data for now)
    suspend fun getDailyTasks(): List<TaskModel> {
        return listOf(
            TaskModel(id = "1", title = "Play 1 Match", reward = 100),
            TaskModel(id = "2", title = "Login Today", reward = 50),
            TaskModel(id = "3", title = "Refer a Friend", reward = 200)
        )
    }

    // ✅ Complete a task (adds to deposit balance)
    suspend fun completeTask(userId: String, task: TaskModel): WalletModel {
        return addDepositCoins(userId, task.reward)
    }

    // 🏁 Add winning reward (adds to withdrawable balance)
    suspend fun rewardWinning(userId: String, amount: Int): WalletModel {
        return addWinningCoins(userId, amount)
    }
}
