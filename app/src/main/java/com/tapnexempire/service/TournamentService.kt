package com.tapnexempire.service

import com.tapnexempire.models.Tournament

object TournamentService {
    private val tournaments = listOf(
        Tournament("1", "Ludo Tournament", 10, 100),
        Tournament("2", "Quiz Tournament", 5, 50)
    )

    fun getTournaments(): List<Tournament> = tournaments

    fun joinTournament(id: String): Boolean {
        // Later: call API
        return true
    }
}
