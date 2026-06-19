package com.tapnexempire.domain

object PrizeCalculator {

      // 🏅 Top 10 rank  
    fun calculateTop10Prizes(totalPool: Long): List<Long> {

        val prizePool = (totalPool * 0.3).toLong()

        val percentages = listOf(
            0.30, 0.20, 0.15, 0.10, 0.07,
            0.06, 0.05, 0.04, 0.02, 0.01
        )

        return percentages.map { (it * prizePool).toLong() }
    }
}
