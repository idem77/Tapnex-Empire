package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.repository.WithdrawRepository
import com.tapnexempire.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WithdrawViewModel @Inject constructor(
    private val withdrawRepository: WithdrawRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<WithdrawState>(WithdrawState.Idle)
    val uiState: StateFlow<WithdrawState> = _uiState

    fun withdraw(amountCoins: Int) {
        val userId = authRepository.getCurrentUserId() ?: return

        // ₹200 limit = 2000 coins
        if (amountCoins <= 0 || amountCoins > 2000) {
            _uiState.value = WithdrawState.Error("Daily limit exceeded")
            return
        }

        viewModelScope.launch {
            _uiState.value = WithdrawState.Loading

            val result = withdrawRepository.requestWithdraw(userId, amountCoins)

            _uiState.value = if (result.isSuccess) {
                WithdrawState.Success
            } else {
                WithdrawState.Error(result.exceptionOrNull()?.message ?: "Failed")
            }
        }
    }
}

sealed class WithdrawState {
    object Idle : WithdrawState()
    object Loading : WithdrawState()
    object Success : WithdrawState()
    data class Error(val message: String) : WithdrawState()
}
