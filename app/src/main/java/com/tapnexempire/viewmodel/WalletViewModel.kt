package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
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

    // 🔥 WALLET STATE
    private val _walletState =
        MutableStateFlow<UiState<WalletModel>>(UiState.Loading)

    val walletState: StateFlow<UiState<WalletModel>> =
        _walletState

    // ✅ START WALLET LISTENER
    fun startWalletListener(userId: String) {

        _walletState.value = UiState.Loading

        repo.listenToWallet(userId) { wallet ->

            if (wallet != null) {

                _walletState.value =
                    UiState.Success(wallet)

            } else {

                _walletState.value =
                    UiState.Error("Wallet not found")
            }
        }
    }

    // ✅ ADD COINS + SAVE TRANSACTION
    fun addCoins(userId: String, coins: Int) {

        if (userId.isEmpty()) {
            println("❌ USER ID EMPTY")
            return
        }

        viewModelScope.launch {

            try {

                val db = FirebaseFirestore.getInstance()

                // 🔥 WALLET REF
                val walletRef =
                    db.collection("wallets")
                        .document(userId)

                // 🔥 TRANSACTION REF
                val transactionRef =
                    db.collection("transactions")
                        .document(userId)
                        .collection("history")
                        .document()

                db.runTransaction { transaction ->

                    val snapshot =
                        transaction.get(walletRef)

                    // ✅ CURRENT VALUES
                    val currentDeposit =
                        snapshot.getLong("depositCoins") ?: 0

                    val currentWithdrawable =
                        snapshot.getLong("withdrawableCoins") ?: 0

                    val currentBonus =
                        snapshot.getLong("bonusCoins") ?: 0

                    // ✅ NEW VALUES
                    val newDeposit =
                        currentDeposit + coins

                    val newWithdrawable =
                        currentWithdrawable + coins

                    // ✅ UPDATE WALLET
                    transaction.set(
                        walletRef,

                        mapOf(

                            "depositCoins" to newDeposit,

                            "withdrawableCoins" to newWithdrawable,

                            "bonusCoins" to currentBonus
                        ),

                        SetOptions.merge()
                    )

                    // ✅ SAVE TRANSACTION
                    val transactionData = hashMapOf(

                        "id" to transactionRef.id,

                        "userId" to userId,

                        "type" to "DEPOSIT",

                        "amount" to (coins / 10),

                        "coins" to coins,

                        "status" to "SUCCESS",

                        "description" to
                            "Coins deposited successfully",

                        "createdAt" to
                            System.currentTimeMillis()
                    )

                    transaction.set(
                        transactionRef,
                        transactionData
                    )
                }

                println("✅ Coins Added Successfully")

            } catch (e: Exception) {

                e.printStackTrace()

                println("❌ Add Coins Failed")
            }
        }
    }
}
