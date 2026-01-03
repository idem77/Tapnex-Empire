package com.tapnexempire.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class TournamentResultRepository(
    private val firestore: FirebaseFirestore,
    private val walletRepository: WalletRepository
) {

    suspend fun distributeRewards(
        tournamentId: String,
        isPaid: Boolean
    ) {
        val playersSnap = firestore
            .collection("tournament_players")
            .whereEqualTo("tournamentId", tournamentId)
            .get()
            .await()

        val totalPlayers = playersSnap.size()
        if (totalPlayers == 0) return

        val entryCoins = 250
        val totalPool = totalPlayers * entryCoins
        val empireCut = (totalPool * 0.70).toInt()
        val rewardPool = totalPool - empireCut

        val sortedPlayers = playersSnap.documents.sortedByDescending {
            it.getLong("score") ?: 0
        }

        sortedPlayers.forEachIndexed { index, doc ->
            val rank = index + 1
            val userId = doc.getString("userId") ?: return@forEachIndexed

            val rewardCoins = when (rank) {
                1 -> (rewardPool * 0.40).toInt()
                2 -> (rewardPool * 0.20).toInt()
                3 -> (rewardPool * 0.10).toInt()
                in 4..10 -> (rewardPool * 0.30 / 7).toInt()
                else -> 0
            }

            if (rewardCoins > 0) {
                if (isPaid) {
                    walletRepository.addWithdrawableCoins(userId, rewardCoins)
                } else {
                    walletRepository.addBonusCoins(userId, rewardCoins)
                }
            }

            doc.reference.update("rank", rank)
        }

        firestore.collection("tournaments")
            .document(tournamentId)
            .update("status", "COMPLETED")
            .await()
    }
}
