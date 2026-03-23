package com.tapnexempire.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FieldValue
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import com.tapnexempire.data.model.TournamentModel
import com.tapnexempire.data.model.TransactionModel
import com.tapnexempire.data.model.TransactionType
import com.tapnexempire.data.model.WalletModel
import com.tapnexempire.utils.deductCoins

class TournamentRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    private val tournamentRef = firestore.collection("tournaments")
    private val walletRef = firestore.collection("wallets")

    fun listenToTournaments(onChange: (List<TournamentModel>) -> Unit) {
        tournamentRef.addSnapshotListener { snapshot, _ ->
            val list = snapshot?.documents?.mapNotNull {
                it.toObject(TournamentModel::class.java)?.copy(id = it.id)
            } ?: emptyList()
            onChange(list)
        }
    }

    suspend fun joinTournament(
        tournamentId: String,
        userId: String,
        entryFee: Long
    ): Result<Unit> {

        return try {

            val tournamentDoc = tournamentRef.document(tournamentId)
            val walletDoc = walletRef.document(userId)
            val participantDoc =
                tournamentDoc.collection("participants").document(userId)

            firestore.runTransaction { transaction ->

                val walletSnap = transaction.get(walletDoc)
                val wallet = walletSnap.toObject(WalletModel::class.java)
                    ?: throw Exception("Wallet not found")

                val updatedWallet = deductCoins(wallet, entryFee)

                val tournamentSnap = transaction.get(tournamentDoc)
                val maxPlayers = tournamentSnap.getLong("maxPlayers") ?: 0
                val joined = tournamentSnap.getLong("joinedPlayers") ?: 0

                if (joined >= maxPlayers)
                    throw Exception("Tournament full")

                // Update wallet
                transaction.set(walletDoc, updatedWallet)

                // Increment players safely
                transaction.update(
                    tournamentDoc,
                    "joinedPlayers",
                    FieldValue.increment(1)
                )

                // Add participant
                transaction.set(
                    participantDoc,
                    mapOf(
                        "userId" to userId,
                        "joinedAt" to System.currentTimeMillis()
                    )
                )

                // Add transaction log
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

            }.await()

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
