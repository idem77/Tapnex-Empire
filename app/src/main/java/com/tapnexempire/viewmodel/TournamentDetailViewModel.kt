package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.data.model.TournamentModel
import com.tapnexempire.data.repository.TournamentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TournamentDetailViewModel @Inject constructor(
    private val repository: TournamentRepository
) : ViewModel() {

    private val _tournament = MutableStateFlow<TournamentModel?>(null)
    val tournament: StateFlow<TournamentModel?> = _tournament

    fun loadTournament(tournamentId: String) {
        viewModelScope.launch {
            try {
                val data = repository.getTournamentById(tournamentId)
                _tournament.value = data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
