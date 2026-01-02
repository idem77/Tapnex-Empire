package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.repository.TournamentRepository
import com.tapnexempire.sdk.LudoSdkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TournamentViewModel @Inject constructor(
    private val ludoSdkManager: LudoSdkManager,
    private val tournamentRepository: TournamentRepository
) : ViewModel() {

    fun startTournamentGame(
        tournamentId: String,
        userId: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        ludoSdkManager.startLudoGame(
            tournamentId = tournamentId,
            userId = userId,
            onGameFinished = { score ->
                viewModelScope.launch {
                    try {
                        tournamentRepository.saveTournamentScore(
                            tournamentId = tournamentId,
                            userId = userId,
                            score = score
                        )
                        onSuccess()
                    } catch (e: Exception) {
                        onError(e.message ?: "Score save failed")
                    }
                }
            },
            onGameFailed = { error ->
                onError(error)
            }
        )
    }
}
