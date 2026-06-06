package com.tapnexempire.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.tapnexempire.core.TapnexCoreController
import com.tapnexempire.admin.core.AdminLiveRepository

class AdminViewModel : ViewModel() {

    // =========================
    // 👤 LIVE STATES
    // =========================

    val usersLive = mutableStateListOf<Map<String, Any>>()
    val depositsLive = mutableStateListOf<Map<String, Any>>()
    val withdrawsLive = mutableStateListOf<Map<String, Any>>()

    init {

        AdminLiveRepository.listenUsers {
            usersLive.clear()
            usersLive.addAll(it)
        }

        AdminLiveRepository.listenDeposits {
            depositsLive.clear()
            depositsLive.addAll(it)
        }

        AdminLiveRepository.listenWithdraws {
            withdrawsLive.clear()
            withdrawsLive.addAll(it)
        }
    }

    // =========================
    // 👤 USER ACTIONS
    // =========================

    fun ban(userId: String) = TapnexCoreController.banUser(userId)

    fun unban(userId: String) = TapnexCoreController.unbanUser(userId)

    fun addCoins(userId: String, amount: Long) =
        TapnexCoreController.addCoins(userId, amount, "ADMIN")

    fun setCoins(userId: String, amount: Long) {
        com.google.firebase.firestore.FirebaseFirestore.getInstance()
            .collection("users")
            .document(userId)
            .update("coins", amount)
    }

    // =========================
    // 💰 DEPOSIT
    // =========================

    fun approveDeposit(userId: String, depositId: String, amount: Long) =
        TapnexCoreController.approveDeposit(userId, depositId, amount)

    fun rejectDeposit(depositId: String) =
        TapnexCoreController.rejectDeposit(depositId)

    // =========================
    // 💸 WITHDRAW
    // =========================

    fun approveWithdraw(userId: String, withdrawId: String, amount: Long) =
        TapnexCoreController.approveWithdraw(userId, withdrawId, amount)

    fun rejectWithdraw(withdrawId: String) =
        TapnexCoreController.rejectWithdraw(withdrawId)

    // =========================
    // 🏆 TOURNAMENT (ADD MISSING FUNCTIONS)
    // =========================

    fun updateScore(tournamentId: String, userId: String, score: Long) {
        com.google.firebase.firestore.FirebaseFirestore.getInstance()
            .collection("tournaments")
            .document(tournamentId)
            .collection("participants")
            .document(userId)
            .update("score", score)
    }

    fun setRank(tournamentId: String, userId: String, rank: Long) {
        com.google.firebase.firestore.FirebaseFirestore.getInstance()
            .collection("tournaments")
            .document(tournamentId)
            .collection("participants")
            .document(userId)
            .update("rank", rank)
    }

    fun rewardUser(tournamentId: String, userId: String, coins: Long) {

        val db = com.google.firebase.firestore.FirebaseFirestore.getInstance()

        val ref = db.collection("tournaments")
            .document(tournamentId)
            .collection("participants")
            .document(userId)

        db.runTransaction { tx ->

            val snap = tx.get(ref)
            val rewarded = snap.getBoolean("rewarded") ?: false

            if (rewarded) return@runTransaction

            tx.update(ref, "rewarded", true)
        }

        TapnexCoreController.addCoins(userId, coins, "TOURNAMENT")
    }
}
