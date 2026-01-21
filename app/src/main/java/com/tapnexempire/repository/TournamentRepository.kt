package com.tapnexempire.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class TournamentRepository(
    private val firestore: FirebaseFirestore
) {

    private val tournamentRef = firestore.collection("tournaments")
    private val usersRef = firestore.collection("users")

    suspend fun getAllTournaments() =
        tournamentRef.get().await()
            .documents.mapNotNull { it.toObject(com.tapnexempire.models.TournamentModel::class.java) }

    suspend fun getUserCoins(userId: String): Int {
        val snapshot = usersRef.document(userId).get().await()
        return snapshot.getLong("coins")?.toInt() ?: 0
    }

    suspend fun isUserJoined(tournamentId: String, userId: String): Boolean {
        val snapshot = tournamentRef.document(tournamentId)
            .collection("participants")
            .document(userId)
            .get()
            .await()

        return snapshot.exists()
    }

    suspend fun joinTournament(
        tournamentId: String,
        userId: String,
        entryFee: Int
    ) {
        val tournamentDoc = tournamentRef.document(tournamentId)
        val userDoc = usersRef.document(userId)
        val participantDoc =
            tournamentDoc.collection("participants").document(userId)

        firestore.runTransaction { transaction ->
            val userSnap = transaction.get(userDoc)
            val coins = userSnap.getLong("coins") ?: 0

            if (coins < entryFee) {
                throw Exception("Not enough coins")
            }

            val joined = transaction.get(tournamentDoc)
                .getLong("joinedPlayers") ?: 0

            transaction.update(userDoc, "coins", coins - entryFee)
            transaction.update(tournamentDoc, "joinedPlayers", joined + 1)
            transaction.set(participantDoc, mapOf("joinedAt" to System.currentTimeMillis()))
        }.await()
    }
}
