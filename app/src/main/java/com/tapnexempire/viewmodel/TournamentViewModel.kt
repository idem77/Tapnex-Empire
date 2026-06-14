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
    repo.listenToTournaments { list: List<TournamentModel> ->
        onChange(list)
    }
}

   fun getTournamentById(
    tournamentId: String,
    onResult: (TournamentModel?) -> Unit
) {
    repo.getTournamentById(
        tournamentId,
        onResult
    )
   }

fun joinTournament(

    tournamentId: String,

    userId: String,

    entryFee: Long,

    onResult: (Boolean, String) -> Unit
) {

    repo.joinTournament(

        tournamentId = tournamentId,

        userId = userId,

        entryFee = entryFee,

        onResult = onResult
    )
  }
}
