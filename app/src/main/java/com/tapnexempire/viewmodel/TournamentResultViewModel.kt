package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel

@HiltViewModel
class TournamentResultViewModel @Inject constructor(
    private val repo: TournamentResultRepository
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<Unit>>(UiState.Success(Unit))
    val state: StateFlow<UiState<Unit>> = _state

    fun rewardUser(userId: String, coins: Long) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                repo.rewardUser(userId, coins)
                _state.value = UiState.Success(Unit)
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Reward failed")
            }
        }
    }
}
