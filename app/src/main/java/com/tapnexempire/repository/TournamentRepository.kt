package com.tapnexempire.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TournamentRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    suspend fun saveTournamentScore(
        tournamentId: String,
        userId: String,
        score: Int
    ) {
        val data = hashMapOf(
            "tournamentId" to tournamentId,
            "userId" to userId,
            "score" to score,
            "timestamp" to System.currentTimeMillis()
        )

        firestore
            .collection("tournament_players")
            .add(data)
            .await()
    }
}
