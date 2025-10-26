// TapnexEmpire/app/src/main/java/com/tapnexempire/viewmodel/WalletViewModel.kt
package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.repository.WalletRepository
import com.tapnexempire.models.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val repository: WalletRepository
) : ViewModel() {

    private val _depositBalance = MutableStateFlow(0)
    val depositBalance: StateFlow<Int> = _depositBalance

    private val _withdrawableBalance = MutableStateFlow(0)
    val withdrawableBalance: StateFlow<Int> = _withdrawableBalance

    private val _referralRewards = MutableStateFlow(0)
    val referralRewards: StateFlow<Int> = _referralRewards

    private val _totalCoins = MutableStateFlow(0)
    val totalCoins: StateFlow<Int> = _totalCoins

    private val _transactions = MutableStateFlow<List<String>>(emptyList())
    val transactions: StateFlow<List<String>>=_Transactions

    private val _dailyTasks = MutableStateFlow<List<TaskModel>>(emptyList())
    val dailyTasks: StateFlow<List<TaskModel>> = _dailyTasks

    init {
        refreshWallet()
        loadTasks()
    }

    fun refreshWallet() {
        viewModelScope.launch {
            val walletData = repository.getWalletData()
            _depositBalance.value = walletData.deposit
            _withdrawableBalance.value = walletData.withdrawable
            _referralRewards.value = walletData.referralRewards
            _totalCoins.value = walletData.totalCoins
            _transactions.value = walletData.transactions
        }
    }

    fun deposit(amount: Int) {
        viewModelScope.launch {
            repository.depositCoins(amount)
            refreshWallet()
        }
    }

    fun withdraw(amount: Int) {
        viewModelScope.launch {
            repository.withdrawCoins(amount)
            refreshWallet()
        }
    }

    fun loadTasks() {
        viewModelScope.launch {
            _dailyTasks.value = repository.getDailyTasks()
        }
    }

    fun completeTask(taskId: String) {
        viewModelScope.launch {
            repository.completeTask(taskId)
            loadTasks()
        }
    }
}
