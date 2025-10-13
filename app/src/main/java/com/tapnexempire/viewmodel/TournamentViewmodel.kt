package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.model.TournamentModel
import com.tapnexempire.repository.TournamentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TournamentViewModel : ViewModel() {

    private val repository = TournamentRepository()

    private val _tournaments = MutableStateFlow<List<TournamentModel>>(emptyList())
    val tournaments: StateFlow<List<TournamentModel>> = _tournaments

    private val _selectedTournament = MutableStateFlow<TournamentModel?>(null)
    val selectedTournament: StateFlow<TournamentModel?> = _selectedTournament

    init {
        loadTournaments()
    }

    fun loadTournaments() {
        viewModelScope.launch {
            val data = repository.getTournaments()
            _tournaments.value = data.sortedBy { it.entryFee } // sort by coin category
        }
    }

    fun joinTournament(id: String, userCoins: Int): Boolean {
        val list = _tournaments.value.toMutableList()
        val index = list.indexOfFirst { it.id == id }

        if (index == -1) return false

        val tournament = list[index]
        if (tournament.isFull) return false
        if (userCoins < tournament.entryFee) return false

        // update player count
        val updatedTournament = tournament.copy(
            joinedPlayers = tournament.joinedPlayers + 1,
            isFull = tournament.joinedPlayers + 1 >= tournament.totalPlayers,
            isJoined = true
        )
        list[index] = updatedTournament

        _tournaments.value = list
        _selectedTournament.value = updatedTournament

        return true
    }

    fun getTournamentDetails(id: String): TournamentModel? {
        return _tournaments.value.find { it.id == id }
    }

    fun refresh() {
        loadTournaments()
    }
          }
