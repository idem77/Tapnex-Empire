package com.tapnexempire.service

import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.model.Wallet
import com.tapnexempire.model.Task
import kotlinx.coroutines.tasks.await

class WalletService {

    private val db = FirebaseFirestore.getInstance()

    // 🪙 Get user wallet
    suspend fun getWalletData(userId: String): Wallet {
        val doc = db.collection("wallets").document(userId).get().await()
        return doc.toObject(Wallet::class.java) ?: Wallet(userId = userId)
    }

    // 💰 Deposit coins
    suspend fun depositCoins(userId: String, amount: Int): Wallet {
        val wallet = getWalletData(userId)
        val updatedWallet = wallet.copy(coins = wallet.coins + amount)
        db.collection("wallets").document(userId).set(updatedWallet).await()
        return updatedWallet
    }

    // ❌ Withdraw coins (if enough balance)
    suspend fun withdrawCoins(userId: String, amount: Int): Wallet {
        val wallet = getWalletData(userId)
        val newBalance = (wallet.coins - amount).coerceAtLeast(0)
        val updatedWallet = wallet.copy(coins = newBalance)
        db.collection("wallets").document(userId).set(updatedWallet).await()
        return updatedWallet
    }

    // 🏆 Get Daily Tasks (mock data for now)
    suspend fun getDailyTasks(): List<Task> {
        return listOf(
            Task(id = "1", title = "Play 1 Match", reward = 100),
            Task(id = "2", title = "Login Today", reward = 50),
            Task(id = "3", title = "Refer a Friend", reward = 200)
        )
    }

    // ✅ Mark task completed
    suspend fun completeTask(userId: String, task: Task): Wallet {
        val wallet = depositCoins(userId, task.reward)
        return wallet
    }
}
