package com.tapnexempire.domain

import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.data.model.WalletModel

object TournamentEngine {

    private val firestore = FirebaseFirestore.getInstance()

    fun runTournament(tournamentId: String) {

        val tournamentDoc = firestore.collection("tournaments").document(tournamentId)

        tournamentDoc.collection("participants").get()
            .addOnSuccessListener { snapshot ->

                val players = snapshot.documents.map {
                    val score = (100..1000).random() // 🎮 Hybrid placeholder
                    Pair(it.id, score)
                }

                // 📊 Sort by score
                val sorted = players.sortedByDescending { it.second }

                val totalPool = snapshot.size() * 250L
                val prizes = PrizeCalculator.calculateTop10Prizes(totalPool)

                sorted.take(10).forEachIndexed { index, pair ->

                    val userId = pair.first
                    val reward = prizes[index]

                    val walletRef =
                        firestore.collection("wallets").document(userId)

                    firestore.runTransaction { transaction ->
                        val snap = transaction.get(walletRef)
                        val wallet =
                            snap.toObject(WalletModel::class.java) ?: return@runTransaction

                        transaction.update(
                            walletRef,
                            "withdrawableCoins",
                            wallet.withdrawable + reward
                        )
                    }
                }
            }
    }
}
