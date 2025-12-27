package com.tapnexempire.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class LudoRepository(private val firestore: FirebaseFirestore) {

    suspend fun submitScore(userId: String, tournamentId: String, score: Int) {
        val data = mapOf(
            "userId" to userId,
            "tournamentId" to tournamentId,
            "score" to score
        )
        firestore.collection("tournament_players").add(data).await()
    }

    suspend fun getTournamentPlayers(tournamentId: String): List<Map<String, Any>> {
        val snapshot = firestore.collection("tournament_players")
            .whereEqualTo("tournamentId", tournamentId)
            .get().await()
        return snapshot.documents.map { it.data!! }
    }
}
