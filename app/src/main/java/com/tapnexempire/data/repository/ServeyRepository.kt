package com.tapnexempire.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class SurveyRepository(
    private val firestore: FirebaseFirestore
) {

    suspend fun increaseSurveyCount(userId: String) {
        val ref = firestore.collection("survey_leaderboard")
            .document(userId)

        firestore.runTransaction { transaction ->
            val snapshot = transaction.get(ref)
            val currentCount =
                snapshot.getLong("paidTournamentsPlayed") ?: 0

            transaction.set(
                ref,
                mapOf(
                    "userId" to userId,
                    "paidTournamentsPlayed" to currentCount + 1,
                    "lastUpdated" to System.currentTimeMillis()
                )
            )
        }.await()
    }
}
