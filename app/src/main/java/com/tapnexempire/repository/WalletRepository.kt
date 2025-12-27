package com.tapnexempire.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class WalletRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    suspend fun getWallet(userId: String): Map<String, Long> {
        val doc = firestore.collection("wallets").document(userId).get().await()
        return doc.data ?: mapOf("coins" to 0, "withdrawableCoins" to 0)
    }

    suspend fun depositCoins(userId: String, coins: Int) {
        val ref = firestore.collection("wallets").document(userId)
        firestore.runTransaction {
            val current = it.get(ref).getLong("coins") ?: 0
            it.update(ref, "coins", current + coins)
        }.await()
    }

    suspend fun withdrawCoins(userId: String, coins: Int) {
        val ref = firestore.collection("wallets").document(userId)
        firestore.runTransaction {
            val withdrawable = it.get(ref).getLong("withdrawableCoins") ?: 0
            if (withdrawable >= coins) {
                it.update(ref, "withdrawableCoins", withdrawable - coins)
            }
        }.await()
    }
}
