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

    suspend fun getWallet(userId: String): WalletModel? {
        return walletRef.document(userId)
            .get()
            .await()
            .toObject(WalletModel::class.java)
    }

    suspend fun createWalletIfNotExists(userId: String) {
        val doc = walletRef.document(userId).get().await()

        if (!doc.exists()) {
            walletRef.document(userId)
                .set(WalletModel(userId = userId))
                .await()
        }
    }

    suspend fun addWithdrawable(userId: String, amount: Long) {
        walletRef.document(userId)
            .update(
                "withdrawableCoins",
                FieldValue.increment(amount)
            )
            .await()
    }
}
