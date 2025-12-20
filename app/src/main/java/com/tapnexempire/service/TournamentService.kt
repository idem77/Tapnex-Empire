package com.tapnexempire.service

import com.tapnexempire.models.TournamentModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TournamentService {

    private val _tournaments = MutableStateFlow(
        listOf(
            TournamentModel(
                id = "T1",
                name = "Daily Battle",
                entryFee = 250,
                prizePool = 2500,
                isJoined = false
            ),
            TournamentModel(
                id = "T2",
                name = "Pro Clash",
                entryFee = 500,
                prizePool = 5000,
                isJoined = false
            )
        )
    )

    val tournaments: StateFlow<List<TournamentModel>> = _tournaments

    fun joinTournament(tournamentId: String): TournamentModel? {
        val updated = _tournaments.value.map {
            if (it.id == tournamentId) it.copy(isJoined = true) else it
        }
        _tournaments.value = updated
        return updated.find { it.id == tournamentId }
    }

    fun getMyTournaments(): List<TournamentModel> {
        return _tournaments.value.filter { it.isJoined }
    }
}
