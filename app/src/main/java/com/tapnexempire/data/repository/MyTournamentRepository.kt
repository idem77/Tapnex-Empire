package com.tapnexempire.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.data.model.MyTournamentModel
import javax.inject.Inject

class MyTournamentRepository @Inject constructor() {

    private val db =
        FirebaseFirestore.getInstance()

    fun getMyTournaments(

        userId: String,

        onResult: (List<MyTournamentModel>) -> Unit
    ) {

        db.collection("tournaments")
            .get()
            .addOnSuccessListener { tournaments ->

                val result =
                    mutableListOf<MyTournamentModel>()

                val docs =
                    tournaments.documents

                if (docs.isEmpty()) {

                    onResult(emptyList())
                    return@addOnSuccessListener
                }

                var processed = 0

                docs.forEach { tournament ->

                    val tournamentId =
                        tournament.id

                    db.collection("tournaments")
                        .document(tournamentId)
                        .collection("participants")
                        .document(userId)
                        .get()
                        .addOnSuccessListener { participant ->

                            if (participant.exists()) {

                                result.add(

                                    MyTournamentModel(

                                        tournamentId =
                                            tournamentId,

                                        title =
                                            tournament.getString("title")
                                                ?: "",

                                        status =
                                            tournament.getString("status")
                                                ?: "",

                                        score =
                                            participant.getLong("score")
                                                ?: 0,

                                        rank =
                                            participant.getLong("rank")
                                                ?: 0
                                    )
                                )
                            }

                            processed++

                            if (processed == docs.size) {

                                onResult(result)
                            }
                        }
                }
            }
    }
}
