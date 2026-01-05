package com.tapnexempire.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.models.TournamentModel
import kotlinx.coroutines.tasks.await

class TournamentRepository(
    private val firestore: FirebaseFirestore
) {

    private val tournamentRef = firestore.collection("tournaments")

    suspend fun getAllTournaments(): List<TournamentModel> {
        return tournamentRef.get().await()
            .documents.mapNotNull { it.toObject(TournamentModel::class.java) }
    }

    suspend fun joinTournament(
        tournamentId: String,
        userId: String
    ) {
        val tournamentDoc = tournamentRef.document(tournamentId)

        firestore.runTransaction { transaction ->
            val snapshot = transaction.get(tournamentDoc)
            val joined = snapshot.getLong("joinedPlayers") ?: 0
            transaction.update(tournamentDoc, "joinedPlayers", joined + 1)
        }.await()
    }
}
