package com.tapnexempire.repository

import com.tapnexempire.models.TournamentModel
import javax.inject.Inject
import kotlin.random.Random
import java.util.UUID

class TournamentRepository @Inject constructor() {

    fun getTournaments(): List<TournamentModel> {
        val tournaments = mutableListOf<TournamentModel>()

        val categories = listOf(100, 250, 500)
        val categoryDistribution = mapOf(100 to 40, 250 to 100, 500 to 10)

        categories.forEach { entry ->
            val count = categoryDistribution[entry] ?: 0
            repeat(count) {
                val totalPlayers = 100
                val joinedPlayers = Random.nextInt(40, totalPlayers)
                val prizePool = entry * totalPlayers
                val startTime = System.currentTimeMillis()
                val endTime = startTime + (7 * 60 * 60 * 1000) // 7 hours later

                tournaments.add(
                    TournamentModel(
                        id = UUID.randomUUID().toString(),
                        title = "Tapnex ${entry}-Coin Tournament",
                        entryFee = entry,
                        totalPlayers = totalPlayers,
                        joinedPlayers = joinedPlayers,
                        durationHours = 7,
                        prizePool = prizePool,
                        startTime = startTime,
                        endTime = endTime,
                        category = entry.toString(),
                        isFull = joinedPlayers >= totalPlayers,
                    )
                )
            }
        }

        return tournaments
    }
}
