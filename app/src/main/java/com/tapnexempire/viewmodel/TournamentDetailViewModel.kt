package com.tapnexempire.ui.tournament.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.tapnexempire.repository.TournamentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TournamentDetailState(
    val isLoading: Boolean = true,
    val isJoined: Boolean = false,
    val coins: Int = 0,
    val error: String? = null
)

@HiltViewModel
class TournamentDetailViewModel @Inject constructor(
    private val repository: TournamentRepository,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _state = MutableStateFlow(TournamentDetailState())
    val state: StateFlow<TournamentDetailState> = _state

    private val ENTRY_FEE = 250

    fun loadTournament(tournamentId: String) {
        val userId = auth.currentUser?.uid ?: return

        viewModelScope.launch {
            try {
                val coins = repository.getUserCoins(userId)
                val joined = repository.isUserJoined(tournamentId, userId)

                _state.value = TournamentDetailState(
                    isLoading = false,
                    isJoined = joined,
                    coins = coins
                )
            } catch (e: Exception) {
                _state.value = TournamentDetailState(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun joinTournament(tournamentId: String) {
        val userId = auth.currentUser?.uid ?: return
        val currentCoins = _state.value.coins

        if (currentCoins < ENTRY_FEE) {
            _state.value = _state.value.copy(
                error = "Not enough coins"
            )
            return
        }

        viewModelScope.launch {
            try {
                repository.joinTournament(
                    tournamentId = tournamentId,
                    userId = userId,
                    entryFee = ENTRY_FEE
                )

                _state.value = _state.value.copy(
                    isJoined = true,
                    coins = currentCoins - ENTRY_FEE
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = e.message
                )
            }
        }
    }
}
