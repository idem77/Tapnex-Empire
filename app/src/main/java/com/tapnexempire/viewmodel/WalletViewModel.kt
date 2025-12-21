package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.repository.WalletRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val repository: WalletRepository
) : ViewModel() {

    val walletState = repository.walletState

    init {
        viewModelScope.launch { repository.loadWallet() }
    }

    fun deposit(amount: Int) = viewModelScope.launch {
        repository.addDepositCoins(amount)
    }

    fun claimBonus() = viewModelScope.launch {
        repository.claimDailyBonus()
    }

    fun withdraw(amount: Int) = viewModelScope.launch {
        repository.withdraw(amount)
    }
}
