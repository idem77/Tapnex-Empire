package com.tapnexempire.data.repository

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.data.model.TransactionModel
import com.tapnexempire.data.model.TournamentModel
import com.tapnexempire.data.model.WalletModel
import javax.inject.Inject

class TournamentRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    private val tournamentRef =
        firestore.collection("tournaments")

    private val walletRef =
        firestore.collection("wallets")

    // 👀 Listen tournaments
    fun listenToTournaments(

        onChange: (List<TournamentModel>) -> Unit
    ) {

        tournamentRef
            .addSnapshotListener { snapshot, _ ->

                val list: List<TournamentModel> =

                    snapshot?.documents?.mapNotNull { doc ->

                        doc.toObject(
                            TournamentModel::class.java
                        )?.copy(id = doc.id)

                    } ?: emptyList()

                onChange(list)
            }
    }

        // Tournament get 
        fun getTournamentById(
    tournamentId: String,
    onResult: (TournamentModel?) -> Unit
) {

    tournamentRef
        .document(tournamentId)
        .get()
        .addOnSuccessListener { doc ->

            val tournament =
                doc.toObject(TournamentModel::class.java)
                    ?.copy(id = doc.id)

            onResult(tournament)
        }
        .addOnFailureListener {
            onResult(null)
        }
        }
     

    // 🏆 Join Tournament
    fun joinTournament(

        tournamentId: String,

        userId: String,

        entryFee: Long,

        onResult: (Boolean, String) -> Unit
    ) {

        val tournamentDoc =
            tournamentRef.document(tournamentId)

        val walletDoc =
            walletRef.document(userId)

        val participantDoc =

            tournamentDoc
                .collection("participants")
                .document(userId)

        // ✅ FIXED TRANSACTION PATH
        val txnRef = firestore
            .collection("transactions")
            .document(userId)
            .collection("history")
            .document()

        firestore.runTransaction { transaction ->

            // 💰 WALLET
            val walletSnap =
                transaction.get(walletDoc)

            val wallet =
                walletSnap.toObject(
                    WalletModel::class.java
                )
                    ?: throw Exception("Wallet not found")

            // ❌ INSUFFICIENT BALANCE
            if (wallet.depositCoins < entryFee) {

                throw Exception(
                    "Insufficient deposit coins"
                )
            }

            // 👤 ALREADY JOINED
            val participantSnap =
                transaction.get(participantDoc)

            if (participantSnap.exists()) {

                throw Exception("Already joined")
            }

            // 🏆 TOURNAMENT
            val tournamentSnap =
                transaction.get(tournamentDoc)

            val maxPlayers =
                tournamentSnap.getLong("maxPlayers") ?: 0

            val joinedPlayers =
                tournamentSnap.getLong("joinedPlayers") ?: 0

            // ❌ FULL
            if (joinedPlayers >= maxPlayers) {

                throw Exception("Tournament Full")
            }

            // 💸 DEDUCT COINS
            transaction.update(

                walletDoc,

                "depositCoins",

                wallet.depositCoins - entryFee
            )

            // ➕ INCREASE PLAYERS
            transaction.update(

                tournamentDoc,

                "joinedPlayers",

                FieldValue.increment(1)
            )

            // 👤 SAVE PARTICIPANT
               transaction.set(

    participantDoc,

    mapOf(

        "userId" to userId,

        "score" to 0,

        "rank" to 0,

        "rewarded" to false,

        "joinedAt" to
            System.currentTimeMillis()
    )
)

            // 🧾 SAVE TRANSACTION
            transaction.set(

                txnRef,

                TransactionModel(

                    id = txnRef.id,

                    userId = userId,

                    type = "ENTRY_FEE",

                    amount = entryFee.toInt(),

                    coins = entryFee.toInt(),

                    status = "SUCCESS",

                    description =
                        "Tournament Entry",

                    createdAt =
                        System.currentTimeMillis()
                )
            )
        }

            .addOnSuccessListener {

                onResult(
                    true,
                    "Joined Successfully"
                )
            }

            .addOnFailureListener {

                onResult(
                    false,
                    it.message ?: "Join Failed"
                )
            }
    }
}
