package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.repository.WalletRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WithdrawViewModel @Inject constructor(
    private val walletRepository: WalletRepository
) : ViewModel() {

    fun withdraw(userId: String, amount: Int) {
        viewModelScope.launch {
            try {
                walletRepository.withdrawCoins(userId, amount)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
