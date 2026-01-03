package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.repository.TournamentResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TournamentResultViewModel @Inject constructor(
    private val repository: TournamentResultRepository
) : ViewModel() {

    fun finishTournament(tournamentId: String, isPaid: Boolean) {
        viewModelScope.launch {
            repository.distributeRewards(tournamentId, isPaid)
        }
    }
}
