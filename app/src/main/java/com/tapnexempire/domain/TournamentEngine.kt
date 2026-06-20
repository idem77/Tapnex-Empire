package com.tapnexempire.domain

import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.data.model.WalletModel

object TournamentEngine {

    private val firestore = FirebaseFirestore.getInstance()

    fun runTournament(tournamentId: String) {

        val tournamentRef =
            firestore.collection("tournaments").document(tournamentId)

        tournamentRef.get()
            .addOnSuccessListener { tournamentSnap ->

                val totalPool =
                    tournamentSnap.getLong("prizePool") ?: 0L

                val prizes =
                    PrizeCalculator.calculateTop10Prizes(totalPool)

                tournamentRef.collection("participants")
                    .get()
                    .addOnSuccessListener { snapshot ->

                        // 🧠 STEP 1: SAFE PLAYER LIST
                        val players = snapshot.documents.mapNotNull {

                            val score = it.getLong("score")

                            if (score != null) {
                                Pair(it.id, score)
                            } else null
                        }

                        // 🏁 STEP 2: SORT
                        val sorted =
                            players.sortedByDescending { it.second }

                        // 🔒 STEP 3: LIMIT SAFELY
                        val topPlayers =
                            sorted.take(minOf(10, sorted.size))

                        val safePrizes =
                            prizes.take(topPlayers.size)

                        // 🧩 STEP 4: PROCESS EACH PLAYER
                        topPlayers.forEachIndexed { index, pair ->

                            val userId = pair.first
                            val reward = safePrizes.getOrElse(index) { 0L }

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
                                    participantSnap.getBoolean("rewarded") ?: false

                                // 🏆 UPDATE RANK
transaction.update(
    participantRef,
    mapOf(
        "rank" to (index + 1).toLong()
    )
)

// Tournament Complete
tournamentRef.update(
    mapOf(
        "status" to "COMPLETED",
        "completedAt" to System.currentTimeMillis()
    )
)
                                // 💰 ONLY GIVE REWARD ONCE
                                if (!alreadyRewarded) {

                                    val walletSnap =
                                        transaction.get(walletRef)

                                    val wallet =
                                        walletSnap.toObject(WalletModel::class.java)
                                            ?: return@runTransaction

                                    val newBalance =
                                        wallet.withdrawableCoins + reward

                                    transaction.update(
                                        walletRef,
                                        "withdrawableCoins",
                                        newBalance
                                    )

                                    transaction.update(
                                        participantRef,
                                        "rewarded",
                                        true
                                    )
                        )  
                                }
                            }
                        }
                    }
            }
    }
}
