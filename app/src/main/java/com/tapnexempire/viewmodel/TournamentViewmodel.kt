package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.models.TournamentModel
import com.tapnexempire.repository.TournamentRepository
import com.tapnexempire.repository.WalletRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TournamentViewModel @Inject constructor(
    private val tournamentRepository: TournamentRepository,
    private val walletRepository: WalletRepository
) : ViewModel() {

    val tournaments: StateFlow<List<TournamentModel>> =
        tournamentRepository.tournaments

    fun joinTournament(tournament: TournamentModel): Boolean {
        val wallet = walletRepository.walletState.value

        // Entry fee sirf total coins se check
        if (wallet.totalCoins < tournament.entryFee) return false

        viewModelScope.launch {
            // Coins deduct (deposit first)
            walletRepository.withdrawCoins(tournament.entryFee)

            // Join tournament
            tournamentRepository.joinTournament(tournament.id)
        }
        return true
    }

    fun getMyTournaments(): List<TournamentModel> {
        return tournamentRepository.getMyTournaments()
    }

    // 🔐 Future hook (backend se aayega)
    fun rewardWinner(coins: Int) {
        viewModelScope.launch {
            walletRepository.addWinningCoins(coins)
        }
    }
}
