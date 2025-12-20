package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.repository.WalletRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val repository: WalletRepository
) : ViewModel() {

    val walletState: StateFlow<com.tapnexempire.models.WalletModel> =
        repository.walletState

    fun deposit(amount: Int) {
        viewModelScope.launch {
            repository.depositCoins(amount)
        }
    }

    fun claimDailyBonus(amount: Int = 500) {
        viewModelScope.launch {
            repository.claimDailyBonus(amount)
        }
    }

    fun addWinning(amount: Int) {
        viewModelScope.launch {
            repository.addWinningCoins(amount)
        }
    }

    fun withdraw(amount: Int): Boolean {
        var result = false
        viewModelScope.launch {
            result = repository.withdrawCoins(amount)
        }
        return result
    }
}
