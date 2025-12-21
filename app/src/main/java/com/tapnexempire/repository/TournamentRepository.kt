package com.tapnexempire.repository

import com.tapnexempire.models.TournamentModel
import com.tapnexempire.service.TournamentService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TournamentRepository @Inject constructor(
    private val service: TournamentService,
    private val walletRepository: WalletRepository
) {

    private val _tournaments = MutableStateFlow<List<TournamentModel>>(emptyList())
    val tournaments: StateFlow<List<TournamentModel>> = _tournaments

    suspend fun loadTournaments() {
        _tournaments.value = service.fetchTournaments()
    }

    suspend fun joinTournament(tournamentId: String): Boolean {
        val tournament = _tournaments.value.find { it.id == tournamentId } ?: return false

        // 1️⃣ Deduct entry fee from deposit coins
        val success = walletRepository.deductEntryFee(tournament.entryFee)
        if (!success) return false

        // 2️⃣ Mark joined
        _tournaments.value = _tournaments.value.map {
            if (it.id == tournamentId)
                it.copy(
                    joinedPlayers = it.joinedPlayers + 1,
                    isJoined = true
                )
            else it
        }
        return true
    }

    suspend fun rewardWinner(amount: Int) {
        // Winning coins are withdrawable
        walletRepository.addWinningCoins(amount)
    }
}
