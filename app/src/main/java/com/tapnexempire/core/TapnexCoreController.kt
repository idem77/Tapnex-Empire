package com.tapnexempire.core

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

object TapnexCoreController {

    private val db = FirebaseFirestore.getInstance()

    // 👤 COINS
    fun addCoins(userId: String, amount: Long, reason: String) {
        if (amount <= 0) return

        db.collection("users").document(userId)
            .update(
                mapOf(
                    "coins" to FieldValue.increment(amount),
                    "lastTransactionReason" to reason
                )
            )
    }

    fun removeCoins(userId: String, amount: Long, reason: String) {
        if (amount <= 0) return

        db.collection("users").document(userId)
            .update(
                mapOf(
                    "coins" to FieldValue.increment(-amount),
                    "lastTransactionReason" to reason
                )
            )
    }

    // 🏆 TOURNAMENT
    fun createTournament(title: String, entryFee: Long, prizePool: Long) {
        val id = db.collection("tournaments").document().id

        db.collection("tournaments").document(id).set(
            mapOf(
                "id" to id,
                "title" to title,
                "entryFee" to entryFee,
                "prizePool" to prizePool,
                "status" to "UPCOMING",
                "createdAt" to System.currentTimeMillis()
            )
        )
    }

    fun closeTournament(id: String) {
        db.collection("tournaments").document(id)
            .update("status", "CLOSED")
    }

    fun deleteTournament(id: String) {
        db.collection("tournaments").document(id).delete()
    }

    // 💰 DEPOSIT / WITHDRAW
    fun approveDeposit(userId: String, depositId: String, amount: Long) {

        db.collection("deposits").document(depositId)
            .update("status", "APPROVED")

        addCoins(userId, amount, "DEPOSIT")
    }

    fun approveWithdraw(userId: String, withdrawId: String, amount: Long) {

        db.collection("withdraws").document(withdrawId)
            .update("status", "APPROVED")

        removeCoins(userId, amount, "WITHDRAW")
    }
}
