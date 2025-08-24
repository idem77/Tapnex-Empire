package com.tapnexempire.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapnexempire.components.CoinCard
import com.tapnexempire.components.GameTile

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Welcome to Tapnex Empire", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))

        // Coin card
        CoinCard(coins = 100)

        Spacer(modifier = Modifier.height(16.dp))

        // Example Game tiles
        GameTile(name = "Ludo", coins = 50)
        Spacer(modifier = Modifier.height(8.dp))
        GameTile(name = "Quiz", coins = 30)
    }
}
