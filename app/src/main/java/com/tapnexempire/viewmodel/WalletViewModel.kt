package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.models.WalletModel
import com.tapnexempire.models.TaskModel
import com.tapnexempire.service.WalletService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WalletViewModel : ViewModel() {

    private val walletService = WalletService()

    private val _walletState = MutableStateFlow(WalletModel())
    val walletState: StateFlow<WalletModel> = _walletState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val userId = "testUser123" // TODO: Replace with FirebaseAuth user ID

    init {
        loadWalletData()
    }

    // 🔹 Fetch wallet data from Firebase
    fun loadWalletData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val wallet = walletService.getWalletData(userId)
                _walletState.value = wallet
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    // 💰 Add deposit coins (bonus, referral, task)
    fun addDeposit(amount: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val wallet = walletService.addDepositCoins(userId, amount)
                _walletState.value = wallet
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    // 🏆 Add winning coins
    fun addWinning(amount: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val wallet = walletService.addWinningCoins(userId, amount)
                _walletState.value = wallet
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    // ❌ Withdraw from withdrawable balance only
    fun withdraw(amount: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val wallet = walletService.withdrawCoins(userId, amount)
                _walletState.value = wallet
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    // ✅ Complete a task and reward deposit balance
    fun completeTask(task: TaskModel) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val wallet = walletService.completeTask(userId, task)
                _walletState.value = wallet
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
