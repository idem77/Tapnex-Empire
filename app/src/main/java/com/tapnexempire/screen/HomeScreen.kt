package com.tapnexempire.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapnexempire.components.PrimaryButton

@Composable
fun HomeScreen(
    goToWallet: () -> Unit,
    goToGames: () -> Unit,
    goToTasks: () -> Unit,
    goToRedeem: () -> Unit,
    goToHelp: () -> Unit,
    goToOffers: () -> Unit
) {
    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Welcome King 👑", style = MaterialTheme.typography.headlineSmall)
        PrimaryButton("Wallet", onClick = goToWallet)
        PrimaryButton("Games", onClick = goToGames)
        PrimaryButton("Tasks", onClick = goToTasks)
        PrimaryButton("Redeem", onClick = goToRedeem)
        PrimaryButton("Help", onClick = goToHelp)
        PrimaryButton("Offers", onClick = goToOffers)
    }
}
