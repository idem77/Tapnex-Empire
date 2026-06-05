package com.tapnexempire.core

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

object TapnexCoreController {

    private val db = FirebaseFirestore.getInstance()

    // =========================
    // 👤 COINS SYSTEM
    // =========================

    fun addCoins(userId: String, amount: Long, reason: String) {

        if (amount <= 0) return

        val userRef = db.collection("users").document(userId)

        db.runTransaction { tx ->
            tx.update(userRef, "coins", FieldValue.increment(amount))
            tx.update(userRef, "lastTransactionReason", reason)
        }

        logTransaction(userId, amount, "CREDIT", reason)
    }

    fun removeCoins(userId: String, amount: Long, reason: String) {

        if (amount <= 0) return

        val userRef = db.collection("users").document(userId)

        db.runTransaction { tx ->

            val snap = tx.get(userRef)
            val current = snap.getLong("coins") ?: 0L

            if (current < amount) return@runTransaction

            tx.update(userRef, "coins", FieldValue.increment(-amount))
            tx.update(userRef, "lastTransactionReason", reason)
        }

        logTransaction(userId, amount, "DEBIT", reason)
    }

    // =========================
    // 🏆 TOURNAMENT
    // =========================

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

    fun closeTournament(id: String) {
        db.collection("tournaments").document(id)
            .update("status", "CLOSED")
    }

    fun deleteTournament(id: String) {
        db.collection("tournaments").document(id)
            .delete()
    }

    // =========================
    // 👥 JOIN TOURNAMENT
    // =========================

    fun joinTournament(tournamentId: String, userId: String, userName: String) {

        val participantRef = db.collection("tournaments")
            .document(tournamentId)
            .collection("participants")
            .document(userId)

        val userRef = db.collection("users")
            .document(userId)

        db.runBatch { batch ->

            batch.set(participantRef, mapOf(
                "userId" to userId,
                "name" to userName,
                "score" to 0,
                "rank" to 0,
                "rewarded" to false,
                "joinedAt" to System.currentTimeMillis()
            ))

            batch.update(userRef, "tournamentEntries", FieldValue.increment(1))
        }
    }

    // =========================
    // 📊 SCORE
    // =========================

    fun updateScore(tournamentId: String, userId: String, score: Long) {

        db.collection("tournaments")
            .document(tournamentId)
            .collection("participants")
            .document(userId)
            .update("score", score)
    }

    // =========================
    // 🏅 RANK
    // =========================

    fun setRank(tournamentId: String, userId: String, rank: Long) {

        db.collection("tournaments")
            .document(tournamentId)
            .collection("participants")
            .document(userId)
            .update("rank", rank)
    }

    // =========================
    // 💰 REWARD
    // =========================

    fun rewardUser(tournamentId: String, userId: String, coins: Long) {

        val participantRef = db.collection("tournaments")
            .document(tournamentId)
            .collection("participants")
            .document(userId)

        db.runTransaction { tx ->

            val snap = tx.get(participantRef)
            val rewarded = snap.getBoolean("rewarded") ?: false

            if (rewarded) return@runTransaction

            tx.update(participantRef, "rewarded", true)
        }

        addCoins(userId, coins, "TOURNAMENT_REWARD")
    }

    // =========================
    // 💰 DEPOSIT
    // =========================

    fun approveDeposit(userId: String, depositId: String, amount: Long) {

        val depositRef = db.collection("deposits").document(depositId)

        db.runTransaction { tx ->

            val snap = tx.get(depositRef)
            val status = snap.getString("status")

            if (status == "APPROVED") return@runTransaction

            tx.update(depositRef, "status", "APPROVED")
        }

        addCoins(userId, amount, "DEPOSIT")
    }

    // =========================
    // 💸 WITHDRAW
    // =========================

    fun approveWithdraw(userId: String, withdrawId: String, amount: Long) {

        val withdrawRef = db.collection("withdraws").document(withdrawId)
        val userRef = db.collection("users").document(userId)

        db.runTransaction { tx ->

            val snap = tx.get(withdrawRef)
            val status = snap.getString("status")

            if (status == "APPROVED") return@runTransaction

            val userSnap = tx.get(userRef)
            val coins = userSnap.getLong("coins") ?: 0L

            if (coins < amount) return@runTransaction

            tx.update(userRef, "coins", FieldValue.increment(-amount))
            tx.update(withdrawRef, "status", "APPROVED")
        }
    }

    // =========================
    // 📜 LOGS
    // =========================

    private fun logTransaction(userId: String, amount: Long, type: String, reason: String) {

        db.collection("transactions").add(
            mapOf(
                "userId" to userId,
                "amount" to amount,
                "type" to type,
                "reason" to reason,
                "timestamp" to System.currentTimeMillis()
            )
        )
    }
}
