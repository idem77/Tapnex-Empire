package com.tapnexempire.service

import com.tapnexempire.models.TournamentModel
import kotlinx.coroutines.delay

class TournamentService {

    suspend fun fetchTournaments(): List<TournamentModel> {
        delay(300)
        return listOf(
            TournamentModel(
                id = "T1",
                name = "Daily Battle",
                entryFee = 250,
                prizePool = 3500,
                maxPlayers = 100
            ),
            TournamentModel(
                id = "T2",
                name = "Pro Clash",
                entryFee = 500,
                prizePool = 7000,
                maxPlayers = 100
            )
        )
    }
}
