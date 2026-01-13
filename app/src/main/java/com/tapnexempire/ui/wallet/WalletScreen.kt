package com.tapnexempire.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.viewmodel.WalletViewModel

@Composable
fun WalletScreen(
    userId: String,
    viewModel: WalletViewModel = hiltViewModel()
) {
    val wallet by viewModel.walletState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadWallet(userId)
    }

    when {
        wallet == null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        else -> {
            WalletContent(wallet!!)
        }
    }
}

@Composable
private fun WalletContent(wallet: com.tapnexempire.models.WalletModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "My Wallet 👑",
            style = MaterialTheme.typography.headlineMedium
        )

        WalletCard(title = "Deposit Coins", value = wallet.depositCoins)
        WalletCard(title = "Bonus Coins", value = wallet.bonusCoins)
        WalletCard(title = "Withdrawable Coins", value = wallet.withdrawableCoins)
        WalletCard(title = "Locked Coins", value = wallet.lockedCoins)

        Divider()

        Text(
            text = "Total Earnings: ${wallet.totalEarnings} coins",
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
private fun WalletCard(
    title: String,
    value: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title)
            Text(
                text = value.toString(),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
