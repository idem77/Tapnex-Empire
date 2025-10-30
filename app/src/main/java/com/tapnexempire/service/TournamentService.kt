package com.tapnexempire.service

import com.tapnexempire.models.TournamentModel

object TournamentService {
    private val tournaments = mutableListOf(
        TournamentModel(
            id = "1",
            title = "Ludo Battle 100",
            entryFee = 100,
            totalPlayers = 100,
            joinedPlayers = 60,
            durationHours = 7,
            prizePool = 5000,
            category = "100"
        ),
        TournamentModel(
            id = "2",
            title = "Quiz War 250",
            entryFee = 250,
            totalPlayers = 80,
            joinedPlayers = 45,
            durationHours = 5,
            prizePool = 10000,
            category = "250"
        ),
        TournamentModel(
            id = "3",
            title = "Tapnex Master 500",
            entryFee = 500,
            totalPlayers = 50,
            joinedPlayers = 30,
            durationHours = 3,
            prizePool = 20000,
            category = "500"
        )
    )

    fun getTournaments(): List<TournamentModel> = tournaments

    fun getTournamentById(id: String): TournamentModel? {
        return tournaments.find { it.id == id }
    }

    fun joinTournament(id: String) {
        tournaments.find { it.id == id }?.let {
            val updated = it.copy(isJoined = true, joinedPlayers = it.joinedPlayers + 1)
            val index = tournaments.indexOf(it)
            tournaments[index] = updated
        }
    }
}
