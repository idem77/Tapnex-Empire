package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ListenerRegistration
import com.tapnexempire.data.model.WithdrawHistoryModel
import com.tapnexempire.data.repository.WithdrawHistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class WithdrawHistoryViewModel @Inject constructor(

    private val repository: WithdrawHistoryRepository

) : ViewModel() {

    private val _withdraws =
        MutableStateFlow<List<WithdrawHistoryModel>>(emptyList())

    val withdraws: StateFlow<List<WithdrawHistoryModel>>
        = _withdraws

    private var listener: ListenerRegistration? = null

    fun startListening(userId: String) {

        listener?.remove()

        listener = repository.listenWithdrawHistory(userId) {

            _withdraws.value = it
        }
    }

    override fun onCleared() {
        super.onCleared()
        listener?.remove()
    }
}
