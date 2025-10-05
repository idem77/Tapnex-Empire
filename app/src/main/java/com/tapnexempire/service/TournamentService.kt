package com.tapnexempire.service

import com.tapnexempire.models.Game
import com.tapnexempire.models.Tournament

object TournamentService {
    private val games = listOf(
        Game("1", "Ludo", android.R.drawable.ic_media_play),
        Game("2", "Carrom", android.R.drawable.ic_media_play)
    )

    private val tournaments = listOf(
        Tournament("1", "Ludo Big Prize", 5000, 50),
        Tournament("2", "Carrom League", 3000, 30)
    )

    fun getGames() = games
    fun getTournaments() = tournaments
    fun getTournamentDetail(id: String) = tournaments.find { it.id == id }
    fun joinTournament(id: String) { /* just demo */ }
    fun getMyTournaments() = tournaments.take(1)
}
