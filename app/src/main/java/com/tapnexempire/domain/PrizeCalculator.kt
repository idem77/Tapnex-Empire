package com.tapnexempire.domain

object PrizeCalculator {

    // 🏆 Top 10 distribution (30% pool)
    fun calculateTop10Prizes(totalPool: Long): List<Long> {

        val prizePool = (totalPool * 0.3).toLong()

        val percentages = listOf(
            0.30, // Rank 1
            0.20, // Rank 2
            0.15, // Rank 3
            0.10, // Rank 4
            0.07, // Rank 5
            0.06, // Rank 6
            0.05, // Rank 7
            0.04, // Rank 8
            0.02, // Rank 9
            0.01  // Rank 10
        )

        return percentages.map { (it * prizePool).toLong() }
    }
}
