package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.models.TournamentModel
import com.tapnexempire.repository.TournamentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TournamentViewModel @Inject constructor(
    private val repository: TournamentRepository
) : ViewModel() {

    private val _tournaments = MutableStateFlow<List<TournamentModel>>(emptyList())
    val tournaments: StateFlow<List<TournamentModel>> = _tournaments

    private val _selectedTournament = MutableStateFlow<TournamentModel?>(null)
    val selectedTournament: StateFlow<TournamentModel?> = _selectedTournament

    private val _myTournaments = MutableStateFlow<List<TournamentModel>>(emptyList())
    val myTournaments: StateFlow<List<TournamentModel>> = _myTournaments

    init {
        loadTournaments()
    }

    fun loadTournaments() {
        viewModelScope.launch {
            val data = repository.getTournaments()
            _tournaments.value = data.sortedBy { it.entryFee } // sort by coin category
        }
    }

    fun joinTournament(tournament: TournamentModel) {
        val list = _tournaments.value.toMutableList()
        val index = list.indexOfFirst { it.id == tournament.id }

        if (index == -1 || tournament.isFull) return

        val updatedTournament = tournament.copy(
            joinedPlayers = tournament.joinedPlayers + 1,
            isFull = tournament.joinedPlayers + 1 >= tournament.totalPlayers,
            isJoined = true
        )

        list[index] = updatedTournament
        _tournaments.value = list
        _selectedTournament.value = updatedTournament
        _myTournaments.value = _myTournaments.value + updatedTournament
    }

    fun getTournamentDetails(id: String): TournamentModel? {
        return _tournaments.value.find { it.id == id }
    }

    fun refresh() {
        loadTournaments()
    }
}
