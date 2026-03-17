package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import com.tapnexempire.data.model.TournamentModel
import com.tapnexempire.data.repository.TournamentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TournamentViewModel @Inject constructor(
    private val repository: TournamentRepository
) : ViewModel() {

    private val _tournaments = MutableStateFlow<List<TournamentModel>>(emptyList())
    val tournaments: StateFlow<List<TournamentModel>> = _tournaments

    fun startListening() {
        repository.listenToTournaments {
            _tournaments.value = it
        }
    }
}
