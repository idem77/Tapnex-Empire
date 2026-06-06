package com.tapnexempire.admin.core

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ListenerRegistration

object AdminLiveRepository {

    private val db = FirebaseFirestore.getInstance()

    // =========================
    // 👤 USERS LIVE
    // =========================
    fun listenUsers(onUpdate: (List<Map<String, Any>>) -> Unit): ListenerRegistration {
        return db.collection("users")
            .addSnapshotListener { snap, _ ->
                onUpdate(snap?.documents?.map { it.data ?: emptyMap() } ?: emptyList())
            }
    }

    fun updateCoins(userId: String, amount: Long) {
        db.collection("users").document(userId)
            .update("coins", FieldValue.increment(amount))
    }

    fun setCoins(userId: String, amount: Long) {
        db.collection("users").document(userId)
            .update("coins", amount)
    }

    fun banUser(userId: String) {
        db.collection("users").document(userId)
            .update("banned", true)
    }

    fun unbanUser(userId: String) {
        db.collection("users").document(userId)
            .update("banned", false)
    }

    // =========================
    // 💰 DEPOSITS LIVE
    // =========================
    fun listenDeposits(onUpdate: (List<Map<String, Any>>) -> Unit): ListenerRegistration {
        return db.collection("deposits")
            .whereEqualTo("status", "PENDING")
            .addSnapshotListener { snap, _ ->
                onUpdate(snap?.documents?.map { it.data ?: emptyMap() } ?: emptyList())
            }
    }

    fun approveDeposit(userId: String, depositId: String, amount: Long) {
        db.collection("deposits").document(depositId)
            .update("status", "APPROVED")

        db.collection("users").document(userId)
            .update("coins", FieldValue.increment(amount))
    }

    fun rejectDeposit(depositId: String) {
        db.collection("deposits").document(depositId)
            .update("status", "REJECTED")
    }

    // =========================
    // 💸 WITHDRAW LIVE
    // =========================
    fun listenWithdraws(onUpdate: (List<Map<String, Any>>) -> Unit): ListenerRegistration {
        return db.collection("withdraws")
            .whereEqualTo("status", "PENDING")
            .addSnapshotListener { snap, _ ->
                onUpdate(snap?.documents?.map { it.data ?: emptyMap() } ?: emptyList())
            }
    }

    fun approveWithdraw(userId: String, withdrawId: String, amount: Long) {

        db.collection("withdraws").document(withdrawId)
            .update("status", "APPROVED")

        db.collection("users").document(userId)
            .update("coins", FieldValue.increment(-amount))
    }

    fun rejectWithdraw(withdrawId: String) {
        db.collection("withdraws").document(withdrawId)
            .update("status", "REJECTED")
    }

    // =========================
    // 🏆 TOURNAMENT CONTROL (FULL)
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

    fun listenTournaments(onUpdate: (List<Map<String, Any>>) -> Unit): ListenerRegistration {
        return db.collection("tournaments")
            .addSnapshotListener { snap, _ ->
                onUpdate(snap?.documents?.map { it.data ?: emptyMap() } ?: emptyList())
            }
    }

    // =========================
    // 👥 PARTICIPANTS
    // =========================

    fun listenParticipants(
        tournamentId: String,
        onUpdate: (List<Map<String, Any>>) -> Unit
    ): ListenerRegistration {
        return db.collection("tournaments")
            .document(tournamentId)
            .collection("participants")
            .addSnapshotListener { snap, _ ->
                onUpdate(snap?.documents?.map { it.data ?: emptyMap() } ?: emptyList())
            }
    }

    fun updateScore(tournamentId: String, userId: String, score: Long) {
        db.collection("tournaments")
            .document(tournamentId)
            .collection("participants")
            .document(userId)
            .update("score", score)
    }

    fun setRank(tournamentId: String, userId: String, rank: Long) {
        db.collection("tournaments")
            .document(tournamentId)
            .collection("participants")
            .document(userId)
            .update("rank", rank)
    }

    fun rewardUser(tournamentId: String, userId: String, coins: Long) {

        val ref = db.collection("tournaments")
            .document(tournamentId)
            .collection("participants")
            .document(userId)

        db.runTransaction { tx ->

            val snap = tx.get(ref)
            val rewarded = snap.getBoolean("rewarded") ?: false

            if (!rewarded) {
                tx.update(ref, "rewarded", true)
            }
        }

        db.collection("users")
            .document(userId)
            .update("coins", FieldValue.increment(coins))
    }
}
