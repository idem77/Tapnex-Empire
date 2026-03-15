package com.tapnexempire.data.repository

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import com.tapnexempire.data.model.WalletModel

class WalletRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    private val walletRef = firestore.collection("wallets")

    // 🔹 Get wallet
    suspend fun getWallet(userId: String): WalletModel? {
        return walletRef.document(userId)
            .get()
            .await()
            .toObject(WalletModel::class.java)
    }

    // 🔹 Create wallet if not exists
    suspend fun createWalletIfNotExists(userId: String) {
        val doc = walletRef.document(userId).get().await()

        if (!doc.exists()) {
            walletRef.document(userId)
                .set(WalletModel(userId = userId))
                .await()
        }
    }

    // 🔹 Add withdrawable coins (reward etc.)
    suspend fun addWithdrawable(userId: String, amount: Long) {
        walletRef.document(userId)
            .update(
                "withdrawableCoins",
                FieldValue.increment(amount)
            )
            .await()
    }

    // 🔹 Withdraw coins
    suspend fun withdrawCoins(userId: String, amount: Long): Result<Unit> {
        return try {

            val walletDoc = walletRef.document(userId)

            firestore.runTransaction { transaction ->

                val snapshot = transaction.get(walletDoc)

                val withdrawable =
                    snapshot.getLong("withdrawableCoins") ?: 0

                if (withdrawable < amount) {
                    throw Exception("Not enough withdrawable coins")
                }

                transaction.update(
                    walletDoc,
                    "withdrawableCoins",
                    withdrawable - amount
                )

            }.await()

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
