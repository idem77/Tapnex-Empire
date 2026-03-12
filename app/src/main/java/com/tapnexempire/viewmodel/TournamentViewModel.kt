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
class TournamentViewModel @Inject constructor(
    private val repository: TournamentRepository
) : ViewModel() {

    private val _tournaments = MutableStateFlow<List<TournamentModel>>(emptyList())
    val tournaments: StateFlow<List<TournamentModel>> = _tournaments

    fun loadTournaments() {
        viewModelScope.launch {
            try {
                _tournaments.value = repository.getTournaments()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
