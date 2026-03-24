package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.data.model.WalletModel
import com.tapnexempire.data.repository.WalletRepository
import com.tapnexempire.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val repo: WalletRepository
) : ViewModel() {

    private val _walletState =
        MutableStateFlow<UiState<WalletModel>>(UiState.Loading)
    val walletState: StateFlow<UiState<WalletModel>> = _walletState

    fun loadWallet(userId: String) {
        viewModelScope.launch {
            _walletState.value = UiState.Loading
            try {
                val wallet = repo.getWallet(userId)
                _walletState.value = UiState.Success(wallet)
            } catch (e: Exception) {
                _walletState.value = UiState.Error(e.message ?: "Error")
            }
        }
    }
}
