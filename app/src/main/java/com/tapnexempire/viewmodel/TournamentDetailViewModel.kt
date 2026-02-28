package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.repository.TournamentRepository
import kotlinx.coroutines.launch

class TournamentDetailViewModel(
    private val repository: TournamentRepository
) : ViewModel() {

    fun joinTournament(
        tournamentId: String,
        userId: String,
        entryFee: Long,
        onResult: (Result<Unit>) -> Unit
    ) {
        viewModelScope.launch {
            try {
                repository.joinTournament(
                    tournamentId = tournamentId,
                    userId = userId,
                    entryFee = entryFee
                )
                onResult(Result.success(Unit))
            } catch (e: Exception) {
                onResult(Result.failure(e))
            }
        }
    }
}
