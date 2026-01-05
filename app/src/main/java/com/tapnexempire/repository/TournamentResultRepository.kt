package com.tapnexempire.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.models.TournamentResultModel
import kotlinx.coroutines.tasks.await

class TournamentResultRepository(
    private val firestore: FirebaseFirestore
) {

    suspend fun submitResult(
        tournamentId: String,
        rankedUsers: List<Pair<String, Int>>, // userId to rank
        totalCollectedCoins: Int,
        isPaidTournament: Boolean
    ) {
        if (!isPaidTournament) return

        val empireCoins = (totalCollectedCoins * 0.70).toInt()
        val playerCoins = (totalCollectedCoins * 0.20).toInt()

        val prizePerWinner = playerCoins / rankedUsers.size

        rankedUsers.forEach { (userId, rank) ->
            val result = TournamentResultModel(
                tournamentId = tournamentId,
                userId = userId,
                rank = rank,
                winCoins = prizePerWinner,
                isWithdrawable = rank <= 10
            )

            firestore.collection("tournament_results")
                .add(result)
                .await()

            // add coins to wallet
            firestore.collection("wallets")
                .document(userId)
                .update(
                    mapOf(
                        "withdrawableCoins" to prizePerWinner
                    )
                )
        }

        // Empire profit record
        firestore.collection("empire_earnings")
            .add(
                mapOf(
                    "tournamentId" to tournamentId,
                    "coins" to empireCoins,
                    "createdAt" to System.currentTimeMillis()
                )
            )
    }
}
