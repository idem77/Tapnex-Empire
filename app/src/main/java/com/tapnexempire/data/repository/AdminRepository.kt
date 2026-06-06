package com.tapnexempire.data.repository

import com.tapnexempire.admin.core.AdminLiveRepository

class AdminRepository {

    // 👤 USERS
    fun listenUsers(onUpdate: (List<Map<String, Any>>) -> Unit) =
        AdminLiveRepository.listenUsers(onUpdate)

    fun updateCoins(userId: String, amount: Long) =
        AdminLiveRepository.updateCoins(userId, amount)

    fun setCoins(userId: String, amount: Long) =
        AdminLiveRepository.setCoins(userId, amount)

    fun banUser(userId: String) =
        AdminLiveRepository.banUser(userId)

    fun unbanUser(userId: String) =
        AdminLiveRepository.unbanUser(userId)

    // 💰 DEPOSITS
    fun listenDeposits(onUpdate: (List<Map<String, Any>>) -> Unit) =
        AdminLiveRepository.listenDeposits(onUpdate)

    fun approveDeposit(userId: String, depositId: String, amount: Long) =
        AdminLiveRepository.approveDeposit(userId, depositId, amount)

    fun rejectDeposit(depositId: String) =
        AdminLiveRepository.rejectDeposit(depositId)

    // 💸 WITHDRAW
    fun listenWithdraws(onUpdate: (List<Map<String, Any>>) -> Unit) =
        AdminLiveRepository.listenWithdraws(onUpdate)

    fun approveWithdraw(userId: String, withdrawId: String, amount: Long) =
        AdminLiveRepository.approveWithdraw(userId, withdrawId, amount)

    fun rejectWithdraw(withdrawId: String) =
        AdminLiveRepository.rejectWithdraw(withdrawId)

    // 🏆 TOURNAMENT
    fun createTournament(title: String, entryFee: Long, prizePool: Long) =
        AdminLiveRepository.createTournament(title, entryFee, prizePool)

    fun closeTournament(id: String) =
        AdminLiveRepository.closeTournament(id)

    fun deleteTournament(id: String) =
        AdminLiveRepository.deleteTournament(id)

    fun listenTournaments(onUpdate: (List<Map<String, Any>>) -> Unit) =
        AdminLiveRepository.listenTournaments(onUpdate)

    fun listenParticipants(
        tournamentId: String,
        onUpdate: (List<Map<String, Any>>) -> Unit
    ) = AdminLiveRepository.listenParticipants(tournamentId, onUpdate)

    fun updateScore(tournamentId: String, userId: String, score: Long) =
        AdminLiveRepository.updateScore(tournamentId, userId, score)

    fun setRank(tournamentId: String, userId: String, rank: Long) =
        AdminLiveRepository.setRank(tournamentId, userId, rank)

    fun rewardUser(tournamentId: String, userId: String, coins: Long) =
        AdminLiveRepository.rewardUser(tournamentId, userId, coins)
}
