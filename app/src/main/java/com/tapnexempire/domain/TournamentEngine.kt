package com.tapnex.domain

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Transaction
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class TournamentEngine {

    private val db: FirebaseFirestore = Firebase.firestore

    // ===============================
    // 1️⃣ JOIN TOURNAMENT
    // ===============================
    suspend fun joinTournament(
        userId: String,
        tournamentId: String,
        entryFee: Int
    ): Result<String> {

        return try {

            val tournamentRef = db.collection("tournaments").document(tournamentId)
            val userRef = db.collection("users").document(userId)

            db.runTransaction { transaction ->

                val tournamentSnap = transaction.get(tournamentRef)
                val userSnap = transaction.get(userRef)

                val status = tournamentSnap.getString("status")
                val currentPlayers = tournamentSnap.getLong("currentPlayers") ?: 0
                val totalPool = tournamentSnap.getLong("totalPool") ?: 0
                val balance = userSnap.getLong("balance") ?: 0

                if (status != "OPEN") throw Exception("Tournament Closed")
                if (currentPlayers >= 100) throw Exception("Tournament Full")
                if (balance < entryFee) throw Exception("Insufficient Balance")

                // Deduct Balance
                transaction.update(userRef, "balance", balance - entryFee)

                // Update Tournament
                transaction.update(
                    tournamentRef,
                    mapOf(
                        "currentPlayers" to currentPlayers + 1,
                        "totalPool" to totalPool + entryFee
                    )
                )

                if (currentPlayers + 1 == 100L) {
                    transaction.update(tournamentRef, "status", "FULL")
                }

                // Add Participant
                val participantRef = tournamentRef
                    .collection("participants")
                    .document(userId)

                transaction.set(
                    participantRef,
                    mapOf(
                        "userId" to userId,
                        "score" to 0,
                        "rank" to 0,
                        "joinedAt" to System.currentTimeMillis()
                    )
                )

                // Log Transaction
                addTransaction(transaction, userId, "JOIN", entryFee)
            }.await()

            Result.success("Joined Successfully")

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ===============================
    // 2️⃣ DISTRIBUTE RESULTS
    // ===============================
    suspend fun distributeResults(
        tournamentId: String,
        rankedUserIds: List<String> // Rank 1 → 100
    ): Result<String> {

        return try {

            val tournamentRef = db.collection("tournaments").document(tournamentId)
            val empireRef = db.collection("empireAccount").document("main")

            db.runTransaction { transaction ->

                val tournamentSnap = transaction.get(tournamentRef)
                val totalPool = tournamentSnap.getLong("totalPool")?.toInt() ?: 0
                val entryFee = tournamentSnap.getLong("entryFee")?.toInt() ?: 0

                if (totalPool == 0) throw Exception("Invalid Pool")

                val prize = PrizeCalculator.calculateDistribution(totalPool)

                var empireTotal = prize.empireBase

                // 🔥 Rank 1–3 → Empire
                for (i in 0..2) {
                    empireTotal += entryFee
                }

                // 🔥 Rank 4–10
                for (rank in 4..10) {

                    val userId = rankedUserIds[rank - 1]
                    val reward = prize.rankRewards[rank] ?: 0

                    val userRef = db.collection("users").document(userId)
                    val userSnap = transaction.get(userRef)
                    val currentBalance = userSnap.getLong("balance") ?: 0
                    val currentWin = userSnap.getLong("totalWinnings") ?: 0

                    transaction.update(
                        userRef,
                        mapOf(
                            "balance" to currentBalance + reward,
                            "totalWinnings" to currentWin + reward
                        )
                    )

                    addTransaction(transaction, userId, "WIN", reward)
                }

                // 🔥 Rank 11–100 Motivation
                for (rank in 11..100) {

                    val userId = rankedUserIds[rank - 1]
                    val reward = prize.motivationPerUser

                    val userRef = db.collection("users").document(userId)
                    val userSnap = transaction.get(userRef)
                    val currentBalance = userSnap.getLong("balance") ?: 0

                    transaction.update(
                        userRef,
                        "balance",
                        currentBalance + reward
                    )

                    addTransaction(transaction, userId, "MOTIVATION", reward)
                }

                // 🔥 Update Empire Account
                val empireSnap = transaction.get(empireRef)
                val currentEmpire = empireSnap.getLong("totalRevenue") ?: 0

                transaction.update(
                    empireRef,
                    "totalRevenue",
                    currentEmpire + empireTotal
                )

                // Close Tournament
                transaction.update(tournamentRef, "status", "CLOSED")

            }.await()

            Result.success("Distribution Complete")

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ===============================
    // 3️⃣ SCORE SUBMIT (Hidden Ranking)
    // ===============================
    suspend fun submitScore(
        tournamentId: String,
        userId: String,
        score: Int
    ) {
        val participantRef = db.collection("tournaments")
            .document(tournamentId)
            .collection("participants")
            .document(userId)

        participantRef.update("score", score).await()
    }

    // ===============================
    // 4️⃣ TRANSACTION LOGGER
    // ===============================
    private fun addTransaction(
        transaction: Transaction,
        userId: String,
        type: String,
        amount: Int
    ) {
        val transactionRef = db.collection("transactions").document()

        transaction.set(
            transactionRef,
            mapOf(
                "userId" to userId,
                "type" to type,
                "amount" to amount,
                "timestamp" to System.currentTimeMillis()
            )
        )
    }
}
