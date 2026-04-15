package com.tapnexempire.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class SurveyRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    private val surveyRef = firestore.collection("survey_leaderboard")
    private val walletRef = firestore.collection("wallets")

    // 📊 Listen leaderboard
    fun listenLeaderboard(
        type: String, // "weekly" or "monthly"
        onChange: (List<Pair<String, Long>>) -> Unit
    ) {
        surveyRef.document(type)
            .collection("users")
            .addSnapshotListener { snapshot, _ ->

                val list = snapshot?.documents?.map {
                    val userId = it.id
                    val score = it.getLong("score") ?: 0L
                    Pair(userId, score)
                }?.sortedByDescending { it.second } ?: emptyList()

                onChange(list)
            }
    }

    // 📊 Add score (Tournament participation → Survey entry)
    fun addScore(userId: String, type: String, points: Long) {
        val doc = surveyRef.document(type)
            .collection("users")
            .document(userId)

        firestore.runTransaction { transaction ->

            val snapshot = transaction.get(doc)
            val current = snapshot.getLong("score") ?: 0L

            transaction.set(
                doc,
                mapOf("score" to current + points)
            )
        }
    }

    // 💰 Distribute rewards (Admin trigger)
    fun distributeRewards(type: String, totalPrize: Long) {

        val leaderboardRef = surveyRef.document(type).collection("users")

        leaderboardRef.get().addOnSuccessListener { snapshot ->

            val sorted = snapshot.documents.map {
                Pair(it.id, it.getLong("score") ?: 0L)
            }.sortedByDescending { it.second }

            val top100 = sorted.take(100)

            val totalScore = top100.sumOf { it.second }.coerceAtLeast(1)

            top100.forEach { (userId, score) ->

                val reward =
                    (score.toDouble() / totalScore * totalPrize).toLong()

                val walletDoc = walletRef.document(userId)

                firestore.runTransaction { transaction ->
                    val snap = transaction.get(walletDoc)
                    val current =
                        snap.getLong("withdrawableCoins") ?: 0L

                    transaction.update(
                        walletDoc,
                        "withdrawableCoins",
                        current + reward
                    )
                }
            }
        }
    }
}
