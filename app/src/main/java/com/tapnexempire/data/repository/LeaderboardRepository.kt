package com.tapnexempire.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.tapnexempire.data.model.LeaderboardPlayer
import javax.inject.Inject

class LeaderboardRepository @Inject constructor(

    private val firestore: FirebaseFirestore

) {

    fun listenLeaderboard(

        onResult: (List<LeaderboardPlayer>) -> Unit

    ) {

        firestore.collection("users")

            .orderBy(
                "empirePoints",
                Query.Direction.DESCENDING
            )

            .limit(100)

            .addSnapshotListener { snapshot, _ ->

                val players =

                    snapshot?.documents?.mapNotNull {

                        it.toObject(
                            LeaderboardPlayer::class.java
                        )

                    } ?: emptyList()

                onResult(players)
            }
    }
}
