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

    private val _state = MutableStateFlow<UiState<WalletModel>>(UiState.Loading)
    val state: StateFlow<UiState<WalletModel>> = _state

    fun loadWallet(userId: String) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val wallet = repo.getWallet(userId)
                    ?: throw Exception("Wallet not found")

                _state.value = UiState.Success(wallet)
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Wallet error")
            }
        }
    }
}
