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

    val score =
        it.getLong("score") ?: 0

    Pair(it.id, score)
                }

                // 📊 Sort by score
                val sorted = players.sortedByDescending { it.second }

                val totalPool = snapshot.size() * 250L
                val prizes = PrizeCalculator.calculateTop10Prizes(totalPool)

                sorted.take(10).forEachIndexed { index, pair ->

    val userId = pair.first
    val reward = prizes[index]

    val participantRef =
    tournamentDoc
        .collection("participants")
        .document(userId)

val walletRef =
    firestore.collection("wallets")
        .document(userId)

firestore.runTransaction { transaction ->

    val participantSnap =
        transaction.get(participantRef)

    val rewarded =
        participantSnap.getBoolean("rewarded")
            ?: false

    transaction.update(
        participantRef,
        "rank",
        index + 1
    )

    if (!rewarded) {

        val walletSnap =
            transaction.get(walletRef)

        val wallet =
            walletSnap.toObject(
                WalletModel::class.java
            ) ?: return@runTransaction

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
    }
}
