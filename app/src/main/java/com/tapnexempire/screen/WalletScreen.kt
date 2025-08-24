package com.tapnexempire.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapnexempire.components.CoinCard

@Composable
fun WalletScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "My Wallet", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))

        CoinCard(coins = 200)

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Total Coins: 200", style = MaterialTheme.typography.body1)
    }
}
