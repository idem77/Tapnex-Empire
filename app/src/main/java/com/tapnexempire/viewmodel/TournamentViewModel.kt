package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.data.model.TournamentModel
import com.tapnexempire.data.repository.TournamentRepository
import com.tapnexempire.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TournamentViewModel @Inject constructor(
    private val repo: TournamentRepository
) : ViewModel() {

    private val _tournaments =
        MutableStateFlow<List<TournamentModel>>(emptyList())
    val tournaments: StateFlow<List<TournamentModel>> = _tournaments

    fun startListening() {
        repo.listenToTournaments {
            _tournaments.value = it
        }
    }

    fun joinTournament(
        tournamentId: String,
        userId: String,
        entryFee: Long
    ) {
        viewModelScope.launch {
            repo.joinTournament(tournamentId, userId, entryFee)
        }
    }
}
