package com.tapnexempire.viewmodel

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import androidx.lifecycle.ViewModel

class AdminViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    // 🏆 CREATE TOURNAMENT
    fun createTournament(title: String, entryFee: Long, prizePool: Long) {

        val id = db.collection("tournaments").document().id

        db.collection("tournaments").document(id).set(
            mapOf(
                "id" to id,
                "title" to title,
                "entryFee" to entryFee,
                "prizePool" to prizePool,
                "status" to "UPCOMING",
                "joinedPlayers" to 0,
                "createdAt" to System.currentTimeMillis()
            )
        )
    }

    // 🔒 CLOSE TOURNAMENT
    fun closeTournament(id: String) {
        db.collection("tournaments").document(id)
            .update("status", "CLOSED")
    }

    // ❌ DELETE TOURNAMENT
    fun deleteTournament(id: String) {
        db.collection("tournaments").document(id)
            .delete()
    }

    // 👥 ADD PLAYER + ENTRY COUNT
    fun addParticipant(
        tournamentId: String,
        userId: String,
        userName: String
    ) {

        val participantRef = db.collection("tournaments")
            .document(tournamentId)
            .collection("participants")
            .document(userId)

        val userRef = db.collection("users")
            .document(userId)

        db.runBatch { batch ->

            batch.set(participantRef, mapOf(
                "userId" to userId,
                "score" to 0,
                "rank" to 0,
                "rewarded" to false,
                "joinedAt" to System.currentTimeMillis()
            ))

            batch.update(
                userRef,
                "tournamentEntries",
                FieldValue.increment(1)
            )
        }
    }

    // 📊 SCORE UPDATE
    fun updateScore(
        tournamentId: String,
        userId: String,
        score: Long
    ) {
        db.collection("tournaments")
            .document(tournamentId)
            .collection("participants")
            .document(userId)
            .update("score", score)
    }

    // 🏅 RANK UPDATE
    fun setRank(
        tournamentId: String,
        userId: String,
        rank: Long
    ) {
        db.collection("tournaments")
            .document(tournamentId)
            .collection("participants")
            .document(userId)
            .update("rank", rank)
    }

    // 💰 REWARD USER (MANUAL CONTROL)
    fun rewardUser(
        tournamentId: String,
        userId: String,
        rewardCoins: Long
    ) {

        val participantRef = db.collection("tournaments")
            .document(tournamentId)
            .collection("participants")
            .document(userId)

        val userRef = db.collection("users")
            .document(userId)

        db.runBatch { batch ->

            batch.update(
                userRef,
                "coins",
                FieldValue.increment(rewardCoins)
            )

            batch.update(
                participantRef,
                "rewarded",
                true
            )
        }
    }
}
