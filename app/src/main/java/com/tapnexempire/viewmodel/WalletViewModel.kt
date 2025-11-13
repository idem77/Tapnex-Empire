package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.models.TaskModel
import com.tapnexempire.models.WalletModel
import com.tapnexempire.service.WalletService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WalletViewModel : ViewModel() {

    private val service = WalletService()

    private val _wallet = MutableStateFlow(Wallet())
    val wallet: StateFlow<Wallet> = _wallet

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    // 🪙 Load wallet
    fun loadWallet(userId: String) {
        viewModelScope.launch {
            _loading.value = true
            _wallet.value = service.getWalletData(userId)
            _loading.value = false
        }
    }

    // 💰 Deposit coins
    fun deposit(userId: String, amount: Int) {
        viewModelScope.launch {
            _loading.value = true
            _wallet.value = service.depositCoins(userId, amount)
            _loading.value = false
        }
    }

    // ❌ Withdraw coins
    fun withdraw(userId: String, amount: Int) {
        viewModelScope.launch {
            _loading.value = true
            _wallet.value = service.withdrawCoins(userId, amount)
            _loading.value = false
        }
    }

    // 🏆 Load tasks
    fun loadTasks() {
        viewModelScope.launch {
            _tasks.value = service.getDailyTasks()
        }
    }

    // ✅ Complete a task
    fun completeTask(userId: String, task: Task) {
        viewModelScope.launch {
            _wallet.value = service.completeTask(userId, task)
        }
    }
}
