package com.tapnexempire.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.data.model.WalletModel
import javax.inject.Inject

class WithdrawRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    private val walletRef = firestore.collection("wallets")
    private val withdrawRef = firestore.collection("withdraw_requests")

    fun requestWithdraw(
        userId: String,
        coins: Long,
        onResult: (Boolean, String) -> Unit
    ) {

        val walletDoc = walletRef.document(userId)

        firestore.runTransaction { transaction ->

            val snapshot = transaction.get(walletDoc)
            val wallet = snapshot.toObject(WalletModel::class.java)
                ?: throw Exception("Wallet not found")

            // 💰 Min withdraw check (₹5 = 50 coins)
            if (coins < 50) {
                throw Exception("Minimum withdraw is ₹5")
            }

            // 📅 Daily limit check (₹200 = 2000 coins)
            val currentTime = System.currentTimeMillis()
            val isSameDay =
                (currentTime - wallet.lastWithdrawDate) < (24 * 60 * 60 * 1000)

            val todayWithdrawn =
                if (isSameDay) wallet.dailyWithdrawnCoins else 0

            if (todayWithdrawn + coins > 2000) {
                throw Exception("Daily limit ₹200 reached")
            }

            // 👑 Only withdrawable coins allowed
            if (wallet.withdrawableCoins < coins) {
                throw Exception("Insufficient withdrawable coins")
            }

            // ➖ Deduct coins
            transaction.update(
                walletDoc,
                mapOf(
                    "withdrawableCoins" to (wallet.withdrawableCoins - coins),
                    "dailyWithdrawnCoins" to (todayWithdrawn + coins),
                    "lastWithdrawDate" to currentTime
                )
            )

            // 📝 Create withdraw request
            val requestRef = withdrawRef.document()

            transaction.set(
                requestRef,
                mapOf(
                    "id" to requestRef.id,
                    "userId" to userId,
                    "coins" to coins,
                    "status" to "pending",
                    "createdAt" to currentTime
                )
            )

        }.addOnSuccessListener {
            onResult(true, "Withdraw request sent")
        }.addOnFailureListener {
            onResult(false, it.message ?: "Error")
        }
    }
}
