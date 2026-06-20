package com.tapnexempire.domain

import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.data.model.WalletModel

object TournamentEngine {

private val firestore = FirebaseFirestore.getInstance()

fun runTournament(tournamentId: String) {

    val tournamentRef =
        firestore.collection("tournaments")
            .document(tournamentId)

    tournamentRef.get()
        .addOnSuccessListener { tournamentSnap ->

            val totalPool =
                tournamentSnap.getLong("prizePool") ?: 0L

            val prizes =
                PrizeCalculator.calculateTop10Prizes(totalPool)

            tournamentRef.collection("participants")
                .get()
                .addOnSuccessListener { snapshot ->

                    val players =
                        snapshot.documents.mapNotNull {

                            val score =
                                it.getLong("score")

                            if (score != null)
                                Pair(it.id, score)
                            else
                                null
                        }

                    val sorted =
                        players.sortedByDescending { it.second }

                    val topPlayers =
                        sorted.take(minOf(10, sorted.size))

                    val safePrizes =
                        prizes.take(topPlayers.size)

                    topPlayers.forEachIndexed { index, pair ->

                        val userId = pair.first
                        val reward =
                            safePrizes.getOrElse(index) { 0L }

                        val participantRef =
                            tournamentRef
                                .collection("participants")
                                .document(userId)

                        val walletRef =
                            firestore.collection("wallets")
                                .document(userId)

                        firestore.runTransaction { transaction ->

                            val participantSnap =
                                transaction.get(participantRef)

                            val alreadyRewarded =
                                participantSnap.getBoolean("rewarded")
                                    ?: false

                            // 🏆 Rank
                            transaction.update(
                                participantRef,
                                "rank",
                                (index + 1).toLong()
                            )

                            // 💰 Reward only once
                            if (!alreadyRewarded) {

                                val walletSnap =
                                    transaction.get(walletRef)

                                val wallet =
                                    walletSnap.toObject(
                                        WalletModel::class.java
                                    )
                                        ?: return@runTransaction

                                transaction.update(
                                    walletRef,
                                    "withdrawableCoins",
                                    wallet.withdrawableCoins + reward
                                )

                                transaction.update(
                                    participantRef,
                                    "rewarded",
                                    true
                                )
                            }
                        }
                    }

                    // 🏁 Complete tournament only once
                    tournamentRef.update(
                        mapOf(
                            "status" to "COMPLETED",
                            "completedAt" to System.currentTimeMillis()
                        )
                    )
                }
        }
}

}
