package com.tapnexempire.service

import com.tapnexempire.models.Tournament
import com.tapnexempire.R

object TournamentService {

    private val tournaments = listOf(
        Tournament(
            id = "1",
            name = "Ludo Master League",
            prize = 10000,
            entryFee = 50,
            description = "Join the ultimate Ludo challenge and win big!",
            imageRes = R.drawable.ludo_banner
        ),
        Tournament(
            id = "2",
            name = "Tapnex Pro Battle",
            prize = 5000,
            entryFee = 20,
            description = "Quick tournament, quick cash rewards.",
            imageRes = R.drawable.tapnex_banner
        ),
        Tournament(
            id = "3",
            name = "Weekend Mega Bash",
            prize = 20000,
            entryFee = 100,
            description = "Big weekend tournament with grand prizes!",
            imageRes = R.drawable.weekend_banner
        )
    )

    fun getTournaments(): List<Tournament> = tournaments

    fun getTournamentDetail(id: String): Tournament? {
        return tournaments.find { it.id == id }
    }

    fun joinTournament(id: String): Boolean {
        return tournaments.any { it.id == id }
    }

    fun getMyTournaments(): List<Tournament> {
        // Later: Return joined tournaments from backend
        return tournaments.take(2)
    }
}
