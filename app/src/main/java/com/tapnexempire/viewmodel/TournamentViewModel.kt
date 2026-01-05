package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.models.TournamentModel
import com.tapnexempire.repository.TournamentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TournamentViewModel(
    private val repository: TournamentRepository
) : ViewModel() {

    private val _tournaments = MutableStateFlow<List<TournamentModel>>(emptyList())
    val tournaments: StateFlow<List<TournamentModel>> = _tournaments

    fun loadTournaments() {
        viewModelScope.launch {
            _tournaments.value = repository.getAllTournaments()
        }
    }

    fun joinTournament(tournamentId: String, userId: String) {
        viewModelScope.launch {
            repository.joinTournament(tournamentId, userId)
        }
    }
}
