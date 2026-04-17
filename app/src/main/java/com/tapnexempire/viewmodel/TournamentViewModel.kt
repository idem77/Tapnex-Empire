package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import com.tapnexempire.data.repository.TournamentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TournamentViewModel @Inject constructor(
    private val repo: TournamentRepository
) : ViewModel() {

    // 👇 ADD THIS FUNCTION (IMPORTANT)
    fun listenTournaments(onChange: (List<Map<String, Any>>) -> Unit) {
        repo.listenToTournaments { list ->
            val mappedList = list.map {
                mapOf(
                    "id" to it.id,
                    "name" to it.name,
                    "entryFee" to it.entryFee,
                    "maxPlayers" to it.maxPlayers,
                    "joinedPlayers" to it.joinedPlayers,
                    "createdAt" to it.createdAt
                )
            }
            onChange(mappedList)
        }
    }

    fun joinTournament(
        tournamentId: String,
        userId: String,
        entryFee: Long,
        onResult: (Boolean, String) -> Unit
    ) {
        repo.joinTournament(tournamentId, userId, entryFee, onResult)
    }
}
