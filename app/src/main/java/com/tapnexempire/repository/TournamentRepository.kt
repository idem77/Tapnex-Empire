package com.tapnexempire.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class TournamentRepository(
    private val firestore: FirebaseFirestore
) {

    private val tournamentRef = firestore.collection("tournaments")
    private val walletRef = firestore.collection("wallets")

    suspend fun joinTournament(
        tournamentId: String,
        userId: String,
        entryFee: Long
    ) {
        val tournamentDoc = tournamentRef.document(tournamentId)
        val walletDoc = walletRef.document(userId)
        val participantDoc =
            tournamentDoc.collection("participants").document(userId)

        firestore.runTransaction { transaction ->

            val walletSnap = transaction.get(walletDoc)
            val totalCoins = walletSnap.getLong("totalCoins") ?: 0

            if (totalCoins < entryFee) {
                throw Exception("Not enough coins")
            }

            val participantSnap = transaction.get(participantDoc)
            if (participantSnap.exists()) {
                throw Exception("Already joined")
            }

            val tournamentSnap = transaction.get(tournamentDoc)
            val joined = tournamentSnap.getLong("joinedPlayers") ?: 0
            val maxPlayers = tournamentSnap.getLong("maxPlayers") ?: 0

            if (joined >= maxPlayers) {
                throw Exception("Tournament full")
            }

            transaction.update(walletDoc, "totalCoins", totalCoins - entryFee)
            transaction.update(tournamentDoc, "joinedPlayers", joined + 1)
            transaction.set(participantDoc, mapOf("joinedAt" to System.currentTimeMillis()))
        }.await()
    }
}
