package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.data.repository.WalletRepository
import com.tapnexempire.data.model.Wallet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val walletRepository: WalletRepository
) : ViewModel() {

    private val _walletState = MutableStateFlow<Wallet?>(null)
    val walletState: StateFlow<Wallet?> = _walletState

    fun loadWallet(userId: String) {
        viewModelScope.launch {
            try {
                val wallet = walletRepository.getWallet(userId)
                _walletState.value = wallet
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
