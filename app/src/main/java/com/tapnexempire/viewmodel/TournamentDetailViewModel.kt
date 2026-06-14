package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.data.model.TournamentModel
import com.tapnexempire.data.repository.TournamentRepository
import com.tapnexempire.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TournamentDetailViewModel @Inject constructor(
    private val repository: TournamentRepository
) : ViewModel() {

    private val _state =
        MutableStateFlow<UiState<TournamentModel>>(UiState.Loading)
    val state: StateFlow<UiState<TournamentModel>> = _state

    fun getTournamentById(
    tournamentId: String,
    onResult: (TournamentModel?) -> Unit
) {

    tournamentRef
        .document(tournamentId)
        .get()
        .addOnSuccessListener { doc ->

            val tournament =
                doc.toObject(TournamentModel::class.java)
                    ?.copy(id = doc.id)

            onResult(tournament)
        }
        .addOnFailureListener {

            onResult(null)
        }
    }
