package com.tapnexempire.service

import com.tapnexempire.models.Tournament

object TournamentService {

    private val tournaments = listOf(
        Tournament(id = "1", name = "Daily Ludo Clash", prize = 5000, entryFee = 10),
        Tournament(id = "2", name = "Mega Quiz League", prize = 10000, entryFee = 20),
        Tournament(id = "3", name = "Tapnex Gold Cup", prize = 25000, entryFee = 50)
    )

    fun getTournaments(): List<Tournament> = tournaments

    fun getTournamentDetail(id: String): Tournament? =
        tournaments.find { it.id == id }

    fun joinTournament(id: String): Boolean {
        // Fake join logic
        return tournaments.any { it.id == id }
    }

    fun getMyTournaments(): List<Tournament> =
        tournaments.take(2) // just for demo, returns first 2
}
