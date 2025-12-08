@file:OptIn(ExperimentalMaterial3Api::class)

package com.tapnexempire.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    coins: Int,
    onWalletClick: () -> Unit,
    onTournamentClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Tapnex Empire") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Coins: $coins", style = MaterialTheme.typography.titleLarge)
            Button(onClick = onWalletClick, modifier = Modifier.fillMaxWidth()) {
                Text("Go to Wallet")
            }
            Button(onClick = onTournamentClick, modifier = Modifier.fillMaxWidth()) {
                Text("Play Tournaments")
            }
            Button(onClick = onProfileClick, modifier = Modifier.fillMaxWidth()) {
                Text("View Profile")
            }
        }
    }
}
