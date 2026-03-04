package com.tapnexempire.repository

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class WalletRepository(
    private val firestore: FirebaseFirestore
) {

    private val walletRef = firestore.collection("wallets")

    suspend fun getWallet(userId: String) =
        walletRef.document(userId).get().await()
            .toObject(com.tapnexempire.models.WalletModel::class.java)

    suspend fun createWalletIfNotExists(userId: String) {
        val doc = walletRef.document(userId).get().await()
        if (!doc.exists()) {
            walletRef.document(userId).set(
                com.tapnexempire.models.WalletModel(userId = userId)
            ).await()
        }
    }


        firestore.runTransaction { transaction ->
            val snapshot = transaction.get(docRef)
            val total = snapshot.getLong("totalCoins") ?: 0

            if (total < amount) {
                throw Exception("Insufficient balance")
            }

            transaction.update(docRef, "totalCoins", total - amount)
        }.await()
    }

    suspend fun addWithdrawable(userId: String, amount: Long) {
        walletRef.document(userId)
            .update("withdrawableCoins", FieldValue.increment(amount))
            .await()
    }
}
