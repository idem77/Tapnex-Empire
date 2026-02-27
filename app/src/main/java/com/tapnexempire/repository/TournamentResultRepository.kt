package com.tapnexempire.repository

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class TournamentResultRepository(
    private val firestore: FirebaseFirestore
) {

    suspend fun rewardUser(
        userId: String,
        coins: Long
    ) {
        firestore.collection("wallets")
            .document(userId)
            .update("withdrawableCoins", FieldValue.increment(coins))
            .await()
    }
}
