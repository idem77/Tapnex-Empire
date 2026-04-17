package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import com.tapnexempire.data.model.TournamentModel
import com.tapnexempire.data.repository.TournamentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TournamentViewModel @Inject constructor(
    private val repo: TournamentRepository
) : ViewModel() {

    fun listenTournaments(onChange: (List<TournamentModel>) -> Unit) {
        repo.listenToTournaments { list ->
            onChange(list)
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
