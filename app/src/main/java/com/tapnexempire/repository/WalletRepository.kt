package com.tapnexempire.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class WithdrawRepository(
    private val firestore: FirebaseFirestore
) {

    suspend fun requestWithdraw(
        userId: String,
        amountCoins: Int
    ): Result<Unit> {
        return try {
            val request = hashMapOf(
                "userId" to userId,
                "amountCoins" to amountCoins,
                "amountRupees" to amountCoins * 0.10,
                "status" to "PENDING",
                "createdAt" to System.currentTimeMillis()
            )

            firestore.collection("withdraw_requests")
                .add(request)
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
