package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.data.repository.WithdrawRepository
import com.tapnexempire.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class WithdrawViewModel @Inject constructor(
    private val repo: WithdrawRepository
) : ViewModel() {

    private val _state =
        MutableStateFlow<UiState<String>>(UiState.Loading)
    val state: StateFlow<UiState<String>> = _state

    fun withdraw(userId: String, amount: Long) {
        viewModelScope.launch {
            try {
                repo.requestWithdraw(userId, amount)
                _state.value = UiState.Success("Request Submitted")
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Error")
            }
        }
    }
}
