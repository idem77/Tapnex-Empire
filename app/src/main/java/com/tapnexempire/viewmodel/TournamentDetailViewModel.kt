package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.data.model.TournamentModel
import com.tapnexempire.data.repository.TournamentRepository
import com.tapnexempire.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TournamentDetailViewModel @Inject constructor(
    private val repository: TournamentRepository
) : ViewModel() {

    private val _state =
        MutableStateFlow<UiState<TournamentModel>>(UiState.Loading)
    val state: StateFlow<UiState<TournamentModel>> = _state

    fun loadTournament(id: String) {
        viewModelScope.launch {
            _state.value = UiState.Loading

            try {
                val tournament = TournamentModel(
                    id = id,
                    name = "Demo Tournament",
                    entryFee = 0,
                    maxPlayers = 0,
                    joinedPlayers = 0,
                    createdAt = System.currentTimeMillis()
                )

                _state.value = UiState.Success(tournament)

            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Error")
            }
        }
    }
}
