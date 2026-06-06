package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import com.tapnexempire.core.TapnexCoreController
import com.tapnexempire.data.repository.AdminRepository

class AdminViewModel : ViewModel() {

    private val repo = AdminRepository()

    // 👤 USERS
    fun listenUsers(onUpdate: (List<Map<String, Any>>) -> Unit) {
        repo.listenUsers(onUpdate)
    }

    fun updateCoins(userId: String, amount: Long) {
        repo.updateCoins(userId, amount)
    }

    fun setCoins(userId: String, amount: Long) {
        repo.setCoins(userId, amount)
    }

    fun banUser(userId: String) {
        repo.banUser(userId)
    }

    fun unbanUser(userId: String) {
        repo.unbanUser(userId)
    }

    // 💸 LIVE
    fun listenDeposits(onUpdate: (List<Map<String, Any>>) -> Unit) {
        repo.listenDeposits(onUpdate)
    }

    fun listenWithdraws(onUpdate: (List<Map<String, Any>>) -> Unit) {
        repo.listenWithdraws(onUpdate)
    }

    // 🏆 CORE ACTIONS
    fun createTournament(title: String, entryFee: Long, prizePool: Long) {
        TapnexCoreController.createTournament(title, entryFee, prizePool)
    }

    fun closeTournament(id: String) {
        TapnexCoreController.closeTournament(id)
    }

    fun deleteTournament(id: String) {
        TapnexCoreController.deleteTournament(id)
    }

    fun approveDeposit(userId: String, depositId: String, amount: Long) {
        TapnexCoreController.approveDeposit(userId, depositId, amount)
    }

    fun approveWithdraw(userId: String, withdrawId: String, amount: Long) {
        TapnexCoreController.approveWithdraw(userId, withdrawId, amount)
    }
}
