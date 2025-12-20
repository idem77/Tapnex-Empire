package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import com.tapnexempire.models.TournamentModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TournamentViewModel : ViewModel() {

    private val _tournaments = MutableStateFlow<List<TournamentModel>>(emptyList())
    val tournaments: StateFlow<List<TournamentModel>> = _tournaments

    fun loadTournaments() {
        _tournaments.value = listOf(
            TournamentModel(
                id = "1",
                name = "Daily Battle",
                entryFee = 250,
                prizePool = 10000,
                players = 100
            )
        )
    }

    fun joinTournament(id: String) {
        // Deduct coins later
    }
}
