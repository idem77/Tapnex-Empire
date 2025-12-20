package com.tapnexempire.repository

import com.tapnexempire.models.TournamentModel
import com.tapnexempire.service.TournamentService
import javax.inject.Inject

class TournamentRepository @Inject constructor(
    private val service: TournamentService
) {

    val tournaments = service.tournaments

    fun joinTournament(id: String): TournamentModel? {
        return service.joinTournament(id)
    }

    fun getMyTournaments(): List<TournamentModel> {
        return service.getMyTournaments()
    }
}
