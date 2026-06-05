package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import com.tapnexempire.core.TapnexCoreController

class AdminViewModel : ViewModel() {

    // 🏆 CREATE TOURNAMENT
    fun createTournament(title: String, entryFee: Long, prizePool: Long) {

        TapnexCoreController.createTournament(
            title = title,
            entryFee = entryFee,
            prizePool = prizePool
        )
    }

    // 🔒 CLOSE TOURNAMENT
    fun closeTournament(id: String) {

        TapnexCoreController.closeTournament(id)
    }

    // ❌ DELETE TOURNAMENT
    fun deleteTournament(id: String) {

        TapnexCoreController.deleteTournament(id)
    }

    // 👥 ADD PARTICIPANT (JOIN TOURNAMENT)
    fun addParticipant(
        tournamentId: String,
        userId: String,
        userName: String
    ) {

        TapnexCoreController.joinTournament(
            tournamentId = tournamentId,
            userId = userId,
            userName = userName
        )
    }

    // 📊 UPDATE SCORE
    fun updateScore(
        tournamentId: String,
        userId: String,
        score: Long
    ) {

        TapnexCoreController.updateScore(
            tournamentId,
            userId,
            score
        )
    }

    // 🏅 SET RANK
    fun setRank(
        tournamentId: String,
        userId: String,
        rank: Long
    ) {

        TapnexCoreController.setRank(
            tournamentId,
            userId,
            rank
        )
    }

    // 💰 REWARD USER (SAFE SYSTEM - NO FAKE RISK)
    fun rewardUser(
        tournamentId: String,
        userId: String,
        rewardCoins: Long
    ) {

        TapnexCoreController.rewardUser(
            tournamentId = tournamentId,
            userId = userId,
            coins = rewardCoins
        )
    }
}
