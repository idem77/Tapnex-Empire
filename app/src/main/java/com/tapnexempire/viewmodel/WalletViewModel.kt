package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.models.WalletModel
import com.tapnexempire.repository.WalletRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WalletViewModel(
    private val walletRepository: WalletRepository
) : ViewModel() {

    private val _walletState = MutableStateFlow<WalletModel?>(null)
    val walletState: StateFlow<WalletModel?> = _walletState

    fun loadWallet(userId: String) {
        viewModelScope.launch {
            walletRepository.createWalletIfNotExists(userId)
            _walletState.value = walletRepository.getWallet(userId)
        }
    }
}
