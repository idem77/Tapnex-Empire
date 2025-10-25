package com.tapnexempire.service

import com.tapnexempire.models.TournamentModel
import com.tapnexempire.models.User
import com.tapnexempire.service.WalletService
import kotlin.random.Random

object TournamentLogic {

    /**
     * User joins a tournament if they have enough balance.
     * Priority: Deposit coins → Winning coins.
     */
    fun joinTournament(user: User, tournament: Tournament): Boolean {
        val entryFee = tournament.entryFee

        return when {
            WalletService.getDepositBalance(user.id) >= entryFee -> {
                WalletService.deductDeposit(user.id, entryFee)
                tournament.players.add(user)
                true
            }
            WalletService.getWithdrawableBalance(user.id) >= entryFee -> {
                WalletService.deductWithdrawable(user.id, entryFee)
                tournament.players.add(user)
                true
            }
            else -> false // Not enough balance
        }
    }

    /**
     * Finalizes a tournament, picks winners, and distributes rewards.
     * Top 1–3 → dummy (Tapnex profit)
     * Rank 4–10 → real user winners
     * 30% → platform cut
     */
    fun finalizeTournament(tournament: Tournament) {
        val totalPool = tournament.entryFee * tournament.players.size
        val platformCut = totalPool * 0.30  // 30%
        val remainingPrize = totalPool - platformCut

        // Pick top 1–3 dummy winners (platform profit)
        val dummyWinners = generateDummyWinners(3)
        val realPlayers = tournament.players.shuffled()

        // Real winners (rank 4–10)
        val realWinners = realPlayers.take(7)

        // Prize distribution: 4–10 ranks get share of 3000 coins from 7000
        val totalRealPrize = remainingPrize * 0.3
        val prizePerWinner = totalRealPrize / realWinners.size

        realWinners.forEach { player ->
            WalletService.addWithdrawable(player.id, prizePerWinner)
        }

        // Platform profit = cut + dummy portion (70%)
        val platformProfit = platformCut + (remainingPrize * 0.7)
        tournament.platformProfit = platformProfit

        // Save result (dummy)
        tournament.dummyWinners = dummyWinners
        tournament.realWinners = realWinners.map { it.id }

        println("Tournament ${tournament.id} finished:")
        println("→ Platform Profit: $platformProfit coins")
        println("→ Real Winners: ${realWinners.map { it.name }}")
    }

    /**
     * Generates fake winners to simulate competition.
     */
    private fun generateDummyWinners(count: Int): List<String> {
        return List(count) { "DummyPlayer${Random.nextInt(1000, 9999)}" }
    }
}
