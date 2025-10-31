package com.tapnexempire.service

import com.tapnexempire.repository.WalletRepository
import javax.inject.Inject

class WalletService @Inject constructor(
    private val walletRepository: WalletRepository
) {

    suspend fun depositCoins(amount: Int) {
        walletRepository.depositCoins(amount)
    }

    suspend fun withdrawCoins(amount: Int) {
        walletRepository.withdrawCoins(amount)
    }

    suspend fun getWalletData() = walletRepository.getWalletData()

    suspend fun getDailyTasks() = walletRepository.getDailyTasks()

    suspend fun completeTask(taskId: String) {
        walletRepository.completeTask(taskId)
    }
}
