package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.repository.AuthRepository
import com.tapnexempire.repository.TournamentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TournamentViewModel @Inject constructor(
    private val authRepo: AuthRepository,
    private val repo: TournamentRepository
) : ViewModel() {

    fun joinTournament(tournamentId: String, score: Int) {
        viewModelScope.launch {
            authRepo.getCurrentUserId()?.let {
                repo.joinTournament(tournamentId, it, score)
            }
        }
    }
}
