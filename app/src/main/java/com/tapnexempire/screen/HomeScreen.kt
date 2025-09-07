package com.tapnexempire.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onWalletClick: () -> Unit,
    onGameClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Welcome, In Tapnex empire!", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        Button(onClick = onWalletClick) {
            Text("Go to Wallet")
        }
        Spacer(Modifier.height(8.dp))

        Button(onClick = onGameClick) {
            Text("Play Ludo")
        }
    }
}
