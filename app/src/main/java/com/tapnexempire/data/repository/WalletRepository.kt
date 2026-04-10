package com.tapnexempire.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FieldValue
import com.tapnexempire.data.model.WalletModel
import javax.inject.Inject

class WalletRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    private val walletRef = firestore.collection("wallets")

    // 👀 Real-time wallet listener
    fun listenToWallet(userId: String, onChange: (WalletModel?) -> Unit) {
        walletRef.document(userId)
            .addSnapshotListener { snapshot, _ ->
                onChange(snapshot?.toObject(WalletModel::class.java))
            }
    }

    // 💰 Add deposit coins
    fun addDepositCoins(userId: String, coins: Long) {
        walletRef.document(userId)
            .update("depositCoins", FieldValue.increment(coins))
    }

    // 🏆 Deduct ONLY deposit coins (Tournament)
    fun deductDepositCoins(
        userId: String,
        coins: Long,
        onResult: (Boolean, String) -> Unit
    ) {
        val doc = walletRef.document(userId)

        firestore.runTransaction { transaction ->
            val snapshot = transaction.get(doc)
            val wallet = snapshot.toObject(WalletModel::class.java)
                ?: throw Exception("Wallet not found")

            if (wallet.depositCoins < coins) {
                throw Exception("Insufficient deposit coins")
            }

            transaction.update(doc, "depositCoins", wallet.depositCoins - coins)

        }.addOnSuccessListener {
            onResult(true, "Success")
        }.addOnFailureListener {
            onResult(false, it.message ?: "Error")
        }
    }

    // 👑 Add winning coins
    fun addWinningCoins(userId: String, coins: Long) {
        walletRef.document(userId)
            .update("winningCoins", FieldValue.increment(coins))
    }

    // 🎁 Add bonus coins
    fun addBonusCoins(userId: String, coins: Long) {
        walletRef.document(userId)
            .update("bonusCoins", FieldValue.increment(coins))
    }

    // 🎁 Create withdraw request
    fun createWithdrawRequest(userId: String, coins: Long) {
        val request = hashMapOf(
            "userId" to userId,
            "coins" to coins,
            "status" to "pending",
            "createdAt" to System.currentTimeMillis()
        )

        firestore.collection("withdraw_requests").add(request)
    }
}
