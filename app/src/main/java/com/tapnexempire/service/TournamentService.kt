package com.tapnexempire.service

import com.tapnexempire.models.Tournament

object TournamentService {
    private val tournaments = listOf(
        Tournament("1", "Ludo Tournament", "Play and win", 0, 1000),
        Tournament("2", "Quiz Tournament", "Answer questions", 0, 500)
    )

    fun getTournaments(): List<Tournament> = tournaments

    fun getTournamentDetail(id: String): Tournament? {
        return tournaments.find { it.id == id }
    }

    fun joinTournament(id: String): Boolean {
        return tournaments.any { it.id == id }
    }

    fun getMyTournaments(): List<Tournament> {
        // later return user joined tournaments
        return tournaments.take(1)
    }
}
