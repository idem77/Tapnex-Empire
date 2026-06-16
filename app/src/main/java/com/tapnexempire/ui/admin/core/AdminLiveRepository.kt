package com.tapnexempire.admin.core

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

object AdminLiveRepository {

    private val db = FirebaseFirestore.getInstance()

    // =========================
    // 👤 USERS
    // =========================

    fun listenUsers(onUpdate: (List<Map<String, Any>>) -> Unit): ListenerRegistration {
        return db.collection("users")
            .addSnapshotListener { snap, _ ->
                onUpdate(snap?.documents?.map { it.data ?: emptyMap() } ?: emptyList())
            }
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
    // 💰 DEPOSIT REQUESTS
    // =========================

    fun listenDeposits(onUpdate: (List<Map<String, Any>>) -> Unit): ListenerRegistration {
        return db.collection("deposit_requests")
            .whereEqualTo("status", "pending")
            .addSnapshotListener { snap, _ ->
                onUpdate(snap?.documents?.map { it.data ?: emptyMap() } ?: emptyList())
            }
    }

    fun approveDeposit(userId: String, depositId: String, coins: Long) {

    val walletRef = db.collection("wallets").document(userId)
    val depositRef = db.collection("deposit_requests").document(depositId)

    db.runTransaction { tx ->

        tx.update(depositRef, "status", "approved")

        tx.update(walletRef, mapOf(
            "depositCoins" to FieldValue.increment(coins)
        ))
    }

    db.collection("transactions")
        .document(userId)
        .collection("history")
        .add(
            mapOf(
                "type" to "DEPOSIT",
                "coins" to coins,
                "amount" to (coins / 10),
                "status" to "SUCCESS",
                "description" to "Coins deposited successfully",
                "createdAt" to System.currentTimeMillis()
            )
        )
    }
    
    fun rejectDeposit(depositId: String) {
        db.collection("deposit_requests")
            .document(depositId)
            .update("status", "rejected")
    }

    // =========================
    // 💸 WITHDRAW REQUESTS
    // =========================

    fun listenWithdraws(onUpdate: (List<Map<String, Any>>) -> Unit): ListenerRegistration {
        return db.collection("withdraw_requests")
            .whereEqualTo("status", "pending")
            .addSnapshotListener { snap, _ ->
                onUpdate(snap?.documents?.map { it.data ?: emptyMap() } ?: emptyList())
            }
    }

    fun approveWithdraw(userId: String, withdrawId: String, coins: Long) {

        val withdrawRef = db.collection("withdraw_requests").document(withdrawId)

        db.runTransaction { tx ->

            // Only mark approved (NO coin deduction here)
            tx.update(withdrawRef, "status", "approved")
        }

        // Optional: log transaction
        db.collection("transactions")
            .document(userId)
            .collection("history")
            .add(
                mapOf(
                    "type" to "WITHDRAW",
                    "amount" to coins,
                    "status" to "SUCCESS",
                    "createdAt" to System.currentTimeMillis()
                )
            )
    }

    fun rejectWithdraw(withdrawId: String) {
        db.collection("withdraw_requests")
            .document(withdrawId)
            .update("status", "rejected")
    }

    // =========================
    // 🏆 TOURNAMENT CONTROL
    // =========================

    fun createTournament(title: String, entryFee: Long, prizePool: Long, maxPlayers: Long ) {

        val id = db.collection("tournaments").document().id

        db.collection("tournaments").document(id).set(
            mapOf(
                "id" to id,
                "title" to title,
                "entryFee" to entryFee,
                "prizePool" to prizePool,
                "status" to "upcoming",
                "maxPlayers" to maxPlayers,
                "joinedPlayers" to 0,
                "createdAt" to System.currentTimeMillis()
            )
        )
    }

    fun closeTournament(id: String) {
        db.collection("tournaments")
            .document(id)
            .update("status", "closed")
    }

    fun deleteTournament(id: String) {
        db.collection("tournaments")
            .document(id)
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

        db.collection("wallets")
            .document(userId)
            .update("withdrawableCoins", FieldValue.increment(coins))
    }
}
