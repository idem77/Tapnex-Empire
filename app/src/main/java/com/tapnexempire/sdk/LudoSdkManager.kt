package com.tapnexempire.sdk

import android.content.Context

/**
 * This class is ONLY responsible for:
 * 1. Starting the Ludo game
 * 2. Returning game result (score)
 *
 * NO business logic here
 */
class LudoSdkManager(
    private val context: Context
) {

    fun startLudoGame(
        tournamentId: String,
        userId: String,
        onGameFinished: (score: Int) -> Unit,
        onGameFailed: (error: String) -> Unit
    ) {
        try {
            /**
             * ---------------------------------------
             * 🔥 REAL SDK CALL WILL COME HERE LATER
             * ---------------------------------------
             *
             * Example:
             * LudoSDK.launchGame(...)
             */

            // ⚠️ TEMP MOCK RESULT (FOR TESTING)
            val mockScore = (50..150).random()

            // Simulate game finished
            onGameFinished(mockScore)

        } catch (e: Exception) {
            onGameFailed(e.message ?: "Ludo game failed")
        }
    }
}
