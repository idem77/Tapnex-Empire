package com.tapnexempire.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class WithdrawRepository(
    private val firestore: FirebaseFirestore
) {

    suspend fun requestWithdraw(
        userId: String,
        amountCoins: Long
    ): Result<Unit> {
        return try {

            val walletRef = firestore.collection("wallets").document(userId)
            val requestRef = firestore.collection("withdraw_requests")

            firestore.runTransaction { transaction ->

                val walletSnap = transaction.get(walletRef)
                val withdrawable = walletSnap.getLong("withdrawableCoins") ?: 0

                if (withdrawable < amountCoins) {
                    throw Exception("Insufficient withdrawable balance")
                }

                transaction.update(
                    walletRef,
                    "withdrawableCoins",
                    withdrawable - amountCoins
                )

                transaction.set(
                    requestRef.document(),
                    mapOf(
                        "userId" to userId,
                        "amountCoins" to amountCoins,
                        "amountRupees" to amountCoins * 0.10,
                        "status" to "PENDING",
                        "createdAt" to System.currentTimeMillis()
                    )
                )
            }.await()

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
