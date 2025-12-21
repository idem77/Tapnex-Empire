package com.tapnexempire.repository

import com.tapnexempire.models.*
import com.tapnexempire.service.WalletService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WalletRepository @Inject constructor(
    private val service: WalletService
) {

    private val _walletState = MutableStateFlow(WalletModel())
    val walletState: StateFlow<WalletModel> = _walletState

    suspend fun loadWallet() {
        _walletState.value = service.getWallet()
    }

    suspend fun addDepositCoins(amount: Int) {
        val tx = transaction(TransactionType.DEPOSIT, amount, "Deposit")
        updateWallet(
            depositDelta = amount,
            tx = tx
        )
    }

    suspend fun addWinningCoins(amount: Int) {
        val tx = transaction(TransactionType.WIN, amount, "Tournament Win")
        updateWallet(
            winningDelta = amount,
            tx = tx
        )
    }

    suspend fun claimDailyBonus(amount: Int = 50) {
        val tx = transaction(TransactionType.BONUS, amount, "Daily Bonus")
        updateWallet(
            depositDelta = amount,
            tx = tx
        )
    }

    suspend fun deductEntryFee(amount: Int): Boolean {
        val wallet = _walletState.value
        if (wallet.depositCoins < amount) return false

        val tx = transaction(TransactionType.ENTRY_FEE, amount, "Tournament Entry")
        updateWallet(
            depositDelta = -amount,
            tx = tx
        )
        return true
    }

    suspend fun withdraw(amount: Int): Boolean {
        val wallet = _walletState.value
        if (wallet.winningCoins < amount) return false

        val tx = transaction(TransactionType.WITHDRAW, amount, "Withdraw")
        updateWallet(
            winningDelta = -amount,
            tx = tx
        )
        return true
    }

    private suspend fun updateWallet(
        depositDelta: Int = 0,
        winningDelta: Int = 0,
        tx: TransactionModel
    ) {
        val current = _walletState.value
        val updated = current.copy(
            depositCoins = current.depositCoins + depositDelta,
            winningCoins = current.winningCoins + winningDelta,
            transactions = listOf(tx) + current.transactions
        )
        _walletState.value = updated
        service.updateWallet(updated)
    }

    private fun transaction(
        type: TransactionType,
        amount: Int,
        desc: String
    ) = TransactionModel(
        id = UUID.randomUUID().toString(),
        type = type,
        amount = amount,
        timestamp = System.currentTimeMillis(),
        description = desc
    )
}
