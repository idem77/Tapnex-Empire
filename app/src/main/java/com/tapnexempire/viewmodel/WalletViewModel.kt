package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import com.tapnexempire.data.model.WalletModel
import com.tapnexempire.data.repository.WalletRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val repo: WalletRepository
) : ViewModel() {

    private val _walletState = MutableStateFlow<WalletModel?>(null)
    val walletState: StateFlow<WalletModel?> = _walletState

    fun startListening(userId: String) {
        repo.listenToWallet(userId) {
            _walletState.value = it
        }
    }
}
