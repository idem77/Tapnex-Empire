package com.tapnexempire.data.repository

import javax.inject.Inject
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class TournamentResultRepository @Inject constructor() { (
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
