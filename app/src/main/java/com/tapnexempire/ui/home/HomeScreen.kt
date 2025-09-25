package com.tapnexempire.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapnexempire.components.CoinCard
import com.tapnexempire.components.GameTile

data class Game(
    val name: String,
    val reward: Int
)

@Composable
fun HomeScreen(
    coins: Int,
    gameList: List<Game>,
    onGameClick: (Game) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Coin Card at the top
        CoinCard(coins = coins)

        Spacer(modifier = Modifier.height(16.dp))

        // List of Games
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(gameList) { game ->
                GameTile(
                    gameName = game.name,
                    reward = game.reward,
                    onClick = { onGameClick(game) }
                )
            }
        }
    }
}
