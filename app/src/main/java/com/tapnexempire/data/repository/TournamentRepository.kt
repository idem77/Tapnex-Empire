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

    onResult: (Boolean, String) -> Unit
) {

    val db = FirebaseFirestore.getInstance()

    val tournamentRef =
        db.collection("tournaments")
            .document(tournamentId)

    val playerRef =
        db.collection("tournament_players")
            .document(tournamentId)
            .collection("players")
            .document(userId)

    db.runTransaction { transaction ->

        val tournamentSnapshot =
            transaction.get(tournamentRef)

        val joinedPlayers =
            tournamentSnapshot.getLong("joinedPlayers") ?: 0

        val maxPlayers =
            tournamentSnapshot.getLong("maxPlayers") ?: 0

        if (joinedPlayers >= maxPlayers) {

            throw Exception("Tournament Full")
        }

        val playerSnapshot =
            transaction.get(playerRef)

        if (playerSnapshot.exists()) {

            throw Exception("Already Joined")
        }

        transaction.update(

            tournamentRef,

            "joinedPlayers",

            joinedPlayers + 1
        )

        transaction.set(

            playerRef,

            mapOf(

                "userId" to userId,

                "joinedAt" to
                    System.currentTimeMillis()
            )
        )

    }.addOnSuccessListener {

        onResult(true, "Joined Successfully")

    }.addOnFailureListener {

        onResult(false, it.message ?: "Join Failed")
      }
  } 
