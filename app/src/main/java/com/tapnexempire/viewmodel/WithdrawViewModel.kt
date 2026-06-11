package com.tapnexempire.viewmodel
import androidx.lifecycle.ViewModel
import com.tapnexempire.data.repository.WithdrawRepository 
import dagger.hilt.android.lifecycle.HiltViewModel 
import javax.inject.Inject

@HiltViewModel class WithdrawViewModel @Inject constructor( private val repository: WithdrawRepository ) : ViewModel() {

    fun requestWithdraw(
    userId: String,
    coins: Long,
    redeemCode: String,
    onResult: (Boolean, String) -> Unit
) {
    repository.requestWithdraw(
        userId = userId,
        coins = coins,
        redeemCode = redeemCode,
        onResult = onResult
    )
}
