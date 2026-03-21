package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.data.repository.SurveyRepository
import kotlinx.coroutines.launch

@HiltViewModel
class SurveyViewModel @Inject constructor(
    private val repository: SurveyRepository
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<Unit>>(UiState.Success(Unit))
    val state: StateFlow<UiState<Unit>> = _state

    fun onPaidTournamentJoined(userId: String) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                repository.increaseSurveyCount(userId)
                _state.value = UiState.Success(Unit)
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Error")
            }
        }
    }
}
