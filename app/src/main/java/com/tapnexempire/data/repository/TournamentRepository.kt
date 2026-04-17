package com.tapnexempire.data.repository

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.data.model.TransactionModel
import com.tapnexempire.data.model.TransactionType
import com.tapnexempire.data.model.WalletModel
import com.tapnexempire.data.model.TournamentModel
import javax.inject.Inject

class TournamentRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    private val tournamentRef = firestore.collection("tournaments")
    private val walletRef = firestore.collection("wallets")

    // 👀 Listen tournaments
    fun listenToTournaments(onChange: (List<TournamentModel>) -> Unit) {
        tournamentRef.addSnapshotListener { snapshot, _ ->

            val list: List<TournamentModel> =
                snapshot?.documents?.mapNotNull { doc ->
                    doc.toObject(TournamentModel::class.java)?.copy(id = doc.id)
                } ?: emptyList()

            onChange(list)
        }
    }

    // 🏆 Join Tournament (ONLY deposit coins)
    fun joinTournament(
        tournamentId: String,
        userId: String,
        entryFee: Long,
        onResult: (Boolean, String) -> Unit
    ) {

        val tournamentDoc = tournamentRef.document(tournamentId)
        val walletDoc = walletRef.document(userId)
        val participantDoc =
            tournamentDoc.collection("participants").document(userId)

        firestore.runTransaction { transaction ->

            val walletSnap = transaction.get(walletDoc)
            val wallet = walletSnap.toObject(WalletModel::class.java)
                ?: throw Exception("Wallet not found")

            if (wallet.depositCoins < entryFee) {
                throw Exception("Insufficient deposit coins")
            }

            val participantSnap = transaction.get(participantDoc)
            if (participantSnap.exists()) {
                throw Exception("Already joined")
            }

            val tournamentSnap = transaction.get(tournamentDoc)
            val maxPlayers = tournamentSnap.getLong("maxPlayers") ?: 0
            val joined = tournamentSnap.getLong("joinedPlayers") ?: 0

            if (joined >= maxPlayers) {
                throw Exception("Tournament full")
            }

            // 💰 Deduct deposit coins
            transaction.update(
                walletDoc,
                "depositCoins",
                wallet.depositCoins - entryFee
            )

            // ➕ Increase players
            transaction.update(
                tournamentDoc,
                "joinedPlayers",
                FieldValue.increment(1)
            )

            // 👤 Add participant
            transaction.set(
                participantDoc,
                mapOf(
                    "userId" to userId,
                    "score" to 0,
                    "joinedAt" to System.currentTimeMillis()
                )
            )

            // 🧾 Transaction log
            val txnRef = firestore.collection("transactions").document()
            transaction.set(
                txnRef,
                TransactionModel(
                    id = txnRef.id,
                    userId = userId,
                    type = TransactionType.ENTRY_FEE,
                    amount = entryFee,
                    description = "Tournament Entry"
                )
            )

        }.addOnSuccessListener {
            onResult(true, "Joined Successfully")
        }.addOnFailureListener {
            onResult(false, it.message ?: "Error")
        }
    }
}
