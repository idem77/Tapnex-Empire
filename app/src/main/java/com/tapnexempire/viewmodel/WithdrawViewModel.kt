package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import com.tapnexempire.data.repository.WithdrawRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WithdrawViewModel @Inject constructor(
    private val repo: WithdrawRepository
) : ViewModel() {

    fun requestWithdraw(
        userId: String,
        coins: Long,
        onResult: (Boolean, String) -> Unit
    ) {
        repo.requestWithdraw(userId, coins, onResult)
    }
}
