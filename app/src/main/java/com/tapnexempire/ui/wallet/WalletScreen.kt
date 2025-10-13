package com.tapnexempire.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WalletScreen(
    viewModel: WalletViewModel = viewModel()
) {
    val state by viewModel.walletState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("💎 Total Coins: ${state.totalCoins}", style = MaterialTheme.typography.headlineSmall)

        WalletCard(title = "Deposit Coins", coins = state.depositCoins, color = MaterialTheme.colorScheme.primaryContainer)
        WalletCard(title = "Winning Coins", coins = state.winningCoins, color = MaterialTheme.colorScheme.secondaryContainer)

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(onClick = { viewModel.addDepositCoins(500) }) {
                Text("Add / Earn 500")
            }

            Button(onClick = { viewModel.addWinningCoins(200) }) {
                Text("Add Winning 200")
            }
        }

        Button(
            onClick = { viewModel.withdrawCoins(200) },
            enabled = state.winningCoins >= 200
        ) {
            Text("Withdraw 200 Coins")
        }

        Button(
            onClick = { viewModel.deductCoins(150) },
            enabled = state.totalCoins >= 150
        ) {
            Text("Join Tournament (150 Coins)")
        }
    }
}

@Composable
fun WalletCard(title: String, coins: Int, color: androidx.compose.ui.graphics.Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Text(text = "$coins Coins", style = MaterialTheme.typography.titleLarge)
        }
    }
}
