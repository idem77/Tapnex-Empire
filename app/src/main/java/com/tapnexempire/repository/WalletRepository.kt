package com.tapnexempire.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.models.TransactionModel
import kotlinx.coroutines.tasks.await

class WalletRepository(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {

    private val userId: String
        get() = auth.currentUser?.uid ?: throw Exception("User not logged in")

    private val userWalletRef
        get() = firestore.collection("users").document(userId).collection("wallet")

    // Fetch total coins
    suspend fun getTotalCoins(): Int {
        val doc = firestore.collection("users").document(userId).get().await()
        return doc.getLong("coins")?.toInt() ?: 0
    }

    // Deposit coins
    suspend fun depositCoins(amount: Int) {
        val currentCoins = getTotalCoins()
        firestore.collection("users").document(userId)
            .update("coins", currentCoins + amount).await()

        // Add transaction record
        val tx = TransactionModel(
            id = System.currentTimeMillis(),
            type = "Deposit",
            amount = amount,
            date = System.currentTimeMillis()
        )
        userWalletRef.add(tx).await()
    }

    // Withdraw coins
    suspend fun withdrawCoins(amount: Int) {
        val currentCoins = getTotalCoins()
        if (amount > currentCoins) throw Exception("Not enough coins")

        firestore.collection("users").document(userId)
            .update("coins", currentCoins - amount).await()

        val tx = TransactionModel(
            id = System.currentTimeMillis(),
            type = "Withdraw",
            amount = amount,
            date = System.currentTimeMillis()
        )
        userWalletRef.add(tx).await()
    }

    // Claim daily bonus
    suspend fun claimDailyBonus(bonus: Int) {
        val currentCoins = getTotalCoins()
        firestore.collection("users").document(userId)
            .update("coins", currentCoins + bonus).await()

        val tx = TransactionModel(
            id = System.currentTimeMillis(),
            type = "Daily Bonus",
            amount = bonus,
            date = System.currentTimeMillis()
        )
        userWalletRef.add(tx).await()
    }

    // Get transaction history
    suspend fun getTransactions(): List<TransactionModel> {
        val snapshot = userWalletRef.orderBy("id").get().await()
        return snapshot.documents.map { it.toObject(TransactionModel::class.java)!! }
    }
}
