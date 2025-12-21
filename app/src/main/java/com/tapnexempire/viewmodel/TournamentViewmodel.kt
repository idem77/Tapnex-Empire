package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.repository.TournamentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TournamentViewModel @Inject constructor(
    private val repository: TournamentRepository
) : ViewModel() {

    val tournaments = repository.tournaments

    init {
        viewModelScope.launch { repository.loadTournaments() }
    }

    fun joinTournament(tournamentId: String) = viewModelScope.launch {
        repository.joinTournament(tournamentId)
    }

    fun rewardWinner(amount: Int) = viewModelScope.launch {
        repository.rewardWinner(amount)
    }
}
