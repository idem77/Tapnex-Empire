package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import com.tapnexempire.data.model.TournamentModel
import com.tapnexempire.data.repository.TournamentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TournamentViewModel @Inject constructor(
    private val repository: TournamentRepository
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<List<TournamentModel>>>(UiState.Loading)
    val state: StateFlow<UiState<List<TournamentModel>>> = _state

    init {
        loadTournaments()
    }

    fun loadTournaments() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val data = repository.getTournaments()
                _state.value = UiState.Success(data)
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Error loading tournaments")
            }
        }
    }
}
