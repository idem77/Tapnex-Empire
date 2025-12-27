package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.repository.LudoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LudoViewModel @Inject constructor(
    private val repository: LudoRepository
) : ViewModel() {

    private val _scoreSubmitState = MutableStateFlow(false)
    val scoreSubmitState: StateFlow<Boolean> = _scoreSubmitState

    fun submitScore(userId: String, tournamentId: String, score: Int) {
        viewModelScope.launch {
            try {
                repository.submitScore(userId, tournamentId, score)
                _scoreSubmitState.value = true
            } catch (e: Exception) {
                _scoreSubmitState.value = false
            }
        }
    }
}
