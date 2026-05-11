package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import com.tapnexempire.data.model.TransactionModel
import com.tapnexempire.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repo: TransactionRepository
) : ViewModel() {

    private val _transactions =
        MutableStateFlow<List<TransactionModel>>(emptyList())

    val transactions: StateFlow<List<TransactionModel>> =
        _transactions

    fun startTransactionListener(userId: String) {

        repo.listenToTransactions(userId) { list ->

            _transactions.value = list
        }
    }
}
