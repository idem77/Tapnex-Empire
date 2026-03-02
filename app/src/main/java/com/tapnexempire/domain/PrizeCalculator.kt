package com.tapnex.domain

object PrizeCalculator {

    fun calculateDistribution(totalPool: Int): PrizeResult {

        val empireBase = (totalPool * 0.70).toInt()
        val userPool = totalPool - empireBase

        val rankDistribution = mutableMapOf<Int, Int>()

        val percentages = mapOf(
            4 to 0.20,
            5 to 0.15,
            6 to 0.12,
            7 to 0.10,
            8 to 0.08,
            9 to 0.07,
            10 to 0.06
        )

        var used = 0

        percentages.forEach { (rank, percent) ->
            val reward = (userPool * percent).toInt()
            rankDistribution[rank] = reward
            used += reward
        }

        val motivationPool = userPool - used
        val motivationPerUser = motivationPool / 90

        return PrizeResult(
            empireBase,
            rankDistribution,
            motivationPerUser
        )
    }
}

data class PrizeResult(
    val empireBase: Int,
    val rankRewards: Map<Int, Int>,
    val motivationPerUser: Int
)
