package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import com.tapnexempire.core.TapnexCoreController

class AdminViewModel : ViewModel() {

    fun addCoins(userId: String, amount: Long) {
        TapnexCoreController.addCoins(userId, amount, "ADMIN")
    }

    fun removeCoins(userId: String, amount: Long) {
        TapnexCoreController.removeCoins(userId, amount, "ADMIN")
    }

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

    fun rejectDeposit(depositId: String) {
        TapnexCoreController.rejectDeposit(depositId)
    }

    fun approveWithdraw(userId: String, withdrawId: String, amount: Long) {
        TapnexCoreController.approveWithdraw(userId, withdrawId, amount)
    }

    fun rejectWithdraw(withdrawId: String) {
        TapnexCoreController.rejectWithdraw(withdrawId)
    }
}
