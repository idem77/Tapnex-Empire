package com.tapnexempire.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FieldValue

class AdminRepository {

    private val db = FirebaseFirestore.getInstance()

    // 👤 LIVE USERS
    fun listenUsers(onUpdate: (List<Map<String, Any>>) -> Unit) {
        db.collection("users")
            .addSnapshotListener { snap, _ ->
                onUpdate(snap?.documents?.map { it.data ?: emptyMap() } ?: emptyList())
            }
    }

    fun updateCoins(userId: String, amount: Long) {
        db.collection("users")
            .document(userId)
            .update("coins", FieldValue.increment(amount))
    }

    fun setCoins(userId: String, amount: Long) {
        db.collection("users")
            .document(userId)
            .update("coins", amount)
    }

    fun banUser(userId: String) {
        db.collection("users")
            .document(userId)
            .update("banned", true)
    }

    fun unbanUser(userId: String) {
        db.collection("users")
            .document(userId)
            .update("banned", false)
    }

    // 💸 LIVE DEPOSITS
    fun listenDeposits(onUpdate: (List<Map<String, Any>>) -> Unit) {
        db.collection("deposits")
            .whereEqualTo("status", "PENDING")
            .addSnapshotListener { snap, _ ->
                onUpdate(snap?.documents?.map { it.data ?: emptyMap() } ?: emptyList())
            }
    }

    // 💸 LIVE WITHDRAW
    fun listenWithdraws(onUpdate: (List<Map<String, Any>>) -> Unit) {
        db.collection("withdraws")
            .whereEqualTo("status", "PENDING")
            .addSnapshotListener { snap, _ ->
                onUpdate(snap?.documents?.map { it.data ?: emptyMap() } ?: emptyList())
            }
    }
}
