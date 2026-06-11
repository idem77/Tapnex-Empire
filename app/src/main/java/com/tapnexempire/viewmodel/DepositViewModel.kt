package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import com.tapnexempire.data.repository.DepositRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DepositViewModel @Inject constructor(
    private val repository: DepositRepository
) : ViewModel() {

    fun submitDeposit(
        userId: String,
        amountRupees: Long,
        amountCoins: Long,
        upiRef: String,
        onResult: (Boolean, String) -> Unit
    ) {

        repository.createDepositRequest(
            userId = userId,
            amountRupees = amountRupees,
            amountCoins = amountCoins,
            upiRef = upiRef,
            screenshotUrl = "",
            onResult = onResult
        )
    }
}
