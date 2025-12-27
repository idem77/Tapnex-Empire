package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.repository.AuthRepository
import com.tapnexempire.repository.WalletRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val authRepo: AuthRepository,
    private val walletRepo: WalletRepository
) : ViewModel() {

    private val _wallet = MutableStateFlow<Map<String, Long>>(emptyMap())
    val wallet: StateFlow<Map<String, Long>> = _wallet

    fun loadWallet() {
        viewModelScope.launch {
            authRepo.getCurrentUserId()?.let {
                _wallet.value = walletRepo.getWallet(it)
            }
        }
    }

    fun deposit(coins: Int) {
        viewModelScope.launch {
            authRepo.getCurrentUserId()?.let {
                walletRepo.depositCoins(it, coins)
                loadWallet()
            }
        }
    }

    fun withdraw(coins: Int) {
        viewModelScope.launch {
            authRepo.getCurrentUserId()?.let {
                walletRepo.withdrawCoins(it, coins)
                loadWallet()
            }
        }
    }
}
