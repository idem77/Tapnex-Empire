package com.tapnexempire.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.models.WalletModel
import kotlinx.coroutines.tasks.await

class WalletRepository(
    private val firestore: FirebaseFirestore
) {

    private val walletRef = firestore.collection("wallets")

    suspend fun getWallet(userId: String): WalletModel? {
        val doc = walletRef.document(userId).get().await()
        return doc.toObject(WalletModel::class.java)
    }

    suspend fun createWalletIfNotExists(userId: String) {
        val doc = walletRef.document(userId).get().await()
        if (!doc.exists()) {
            walletRef.document(userId).set(
                WalletModel(userId = userId)
            ).await()
        }
    }

    suspend fun addDepositCoins(userId: String, coins: Int) {
        walletRef.document(userId).update(
            "depositCoins", com.google.firebase.firestore.FieldValue.increment(coins.toLong())
        ).await()
    }

    suspend fun addBonusCoins(userId: String, coins: Int) {
        walletRef.document(userId).update(
            "bonusCoins", com.google.firebase.firestore.FieldValue.increment(coins.toLong())
        ).await()
    }

    suspend fun addWithdrawableCoins(userId: String, coins: Int) {
        walletRef.document(userId).update(
            "withdrawableCoins", com.google.firebase.firestore.FieldValue.increment(coins.toLong())
        ).await()
    }

    suspend fun addLockedCoins(userId: String, coins: Int) {
        walletRef.document(userId).update(
            "lockedCoins", com.google.firebase.firestore.FieldValue.increment(coins.toLong())
        ).await()
    }
}
