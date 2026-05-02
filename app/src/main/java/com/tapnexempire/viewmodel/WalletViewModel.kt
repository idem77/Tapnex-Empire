package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import com.tapnexempire.data.model.WalletModel
import com.tapnexempire.data.repository.WalletRepository
import com.tapnexempire.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val repo: WalletRepository
) : ViewModel() {

    private val _walletState =
        MutableStateFlow<UiState<WalletModel>>(UiState.Loading)
    val walletState: StateFlow<UiState<WalletModel>> = _walletState

    fun startWalletListener(userId: String) {
        _walletState.value = UiState.Loading

        repo.listenToWallet(userId) { wallet ->
            if (wallet != null) {
                _walletState.value = UiState.Success(wallet)
            } else {
                _walletState.value = UiState.Error("Wallet not found")
            }
 fun addCoins(userId: String, coins: Int) {

    viewModelScope.launch {

        try {
            val docRef = FirebaseFirestore.getInstance()
                .collection("wallets")
                .document(userId)

            FirebaseFirestore.getInstance().runTransaction { transaction ->

                val snapshot = transaction.get(docRef)

                val currentDeposit = snapshot.getLong("depositCoins") ?: 0
                val currentWithdrawable = snapshot.getLong("withdrawableCoins") ?: 0

                // 🔥 update values
                val newDeposit = currentDeposit + coins
                val newWithdrawable = currentWithdrawable + coins

                transaction.update(
                    docRef,
                    mapOf(
                        "depositCoins" to newDeposit,
                        "withdrawableCoins" to newWithdrawable
                    )
                )
            }

        } catch (e: Exception) {
            e.printStackTrace()
          }
       }
   }
}
