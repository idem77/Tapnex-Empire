package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import com.tapnexempire.admin.core.AdminLiveRepository

class AdminLiveViewModel : ViewModel() {

    fun listenUsers(onUpdate: (List<Map<String, Any>>) -> Unit) {
        AdminLiveRepository.listenUsers(onUpdate)
    }

    fun listenDeposits(onUpdate: (List<Map<String, Any>>) -> Unit) {
        AdminLiveRepository.listenDeposits(onUpdate)
    }

    fun listenWithdraws(onUpdate: (List<Map<String, Any>>) -> Unit) {
        AdminLiveRepository.listenWithdraws(onUpdate)
    }

    fun addCoins(userId: String, amount: Long) {
        AdminLiveRepository.updateCoins(userId, amount)
    }

    fun setCoins(userId: String, amount: Long) {
        AdminLiveRepository.setCoins(userId, amount)
    }

    fun banUser(userId: String) {
        AdminLiveRepository.banUser(userId)
    }

    fun unbanUser(userId: String) {
        AdminLiveRepository.unbanUser(userId)
    }

    fun approveDeposit(userId: String, depositId: String, amount: Long) {
        AdminLiveRepository.approveDeposit(userId, depositId, amount)
    }

    fun rejectDeposit(depositId: String) {
        AdminLiveRepository.rejectDeposit(depositId)
    }

    fun approveWithdraw(userId: String, withdrawId: String, amount: Long) {
        AdminLiveRepository.approveWithdraw(userId, withdrawId, amount)
    }

    fun rejectWithdraw(withdrawId: String) {
        AdminLiveRepository.rejectWithdraw(withdrawId)
    }
}
