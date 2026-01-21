package com.tapnexempire.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.models.TournamentModel
import kotlinx.coroutines.tasks.await

class TournamentRepository(
    private val firestore: FirebaseFirestore
) {

    private val tournamentRef = firestore.collection("tournaments")
    private val walletRef = firestore.collection("wallets")
    private val participantsRef = firestore.collection("tournament_participants")

    suspend fun getAllTournaments(): List<TournamentModel> {
        return tournamentRef.get().await()
            .documents.mapNotNull { it.toObject(TournamentModel::class.java) }
    }

    // ✅ NEW
    suspend fun getUserCoins(userId: String): Int {
        val walletSnap = walletRef.document(userId).get().await()
        return walletSnap.getLong("depositCoins")?.toInt() ?: 0
    }

    // ✅ NEW
    suspend fun isUserJoined(
        tournamentId: String,
        userId: String
    ): Boolean {
        val docId = "${tournamentId}_$userId"
        return participantsRef.document(docId).get().await().exists()
    }

    // ✅ UPDATED (entryFee added)
    suspend fun joinTournament(
        tournamentId: String,
        userId: String,
        entryFee: Int
    ) {
        val tournamentDoc = tournamentRef.document(tournamentId)
        val walletDoc = walletRef.document(userId)
        val participantDoc = participantsRef.document("${tournamentId}_$userId")

        firestore.runTransaction { transaction ->

            val walletSnap = transaction.get(walletDoc)
            val currentCoins = walletSnap.getLong("depositCoins") ?: 0

            if (currentCoins < entryFee) {
                throw Exception("Not enough coins")
            }

            // deduct coins
            transaction.update(
                walletDoc,
                "depositCoins",
                currentCoins - entryFee
            )

            // increase joined players
            val tournamentSnap = transaction.get(tournamentDoc)
            val joined = tournamentSnap.getLong("joinedPlayers") ?: 0
            transaction.update(tournamentDoc, "joinedPlayers", joined + 1)

            // mark participant
            transaction.set(
                participantDoc,
                mapOf(
                    "tournamentId" to tournamentId,
                    "userId" to userId,
                    "joinedAt" to System.currentTimeMillis()
                )
            )
        }.await()
    }
}
