package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.repository.WithdrawRepository
import kotlinx.coroutines.launch

class WithdrawViewModel(
    private val repository: WithdrawRepository
) : ViewModel() {

    fun requestWithdraw(
        userId: String,
        amountCoins: Long,
        onResult: (Result<Unit>) -> Unit
    ) {
        viewModelScope.launch {
            val result = repository.requestWithdraw(userId, amountCoins)
            onResult(result)
        }
    }
}
