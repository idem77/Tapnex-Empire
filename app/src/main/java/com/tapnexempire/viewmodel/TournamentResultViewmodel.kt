package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.repository.TournamentResultRepository
import kotlinx.coroutines.launch

class TournamentResultViewModel(
    private val repository: TournamentResultRepository
) : ViewModel() {

    fun submitTournamentResult( tournamentId: string)
     {
        viewModelScope.launch {
            repository.submitResult(
                tournamentId,
                rankedUsers,
                totalCollectedCoins,
                isPaidTournament
            )
        }
    }
}
