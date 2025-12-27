package com.tapnexempire.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TournamentRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    suspend fun joinTournament(
        tournamentId: String,
        userId: String,
        score: Int
    ) {
        firestore.collection("tournament_players")
            .add(
                mapOf(
                    "tournamentId" to tournamentId,
                    "userId" to userId,
                    "score" to score,
                    "rank" to 0
                )
            ).await()
    }

    suspend fun getMyTournaments(userId: String) =
        firestore.collection("tournament_players")
            .whereEqualTo("userId", userId)
            .get().await()
}
