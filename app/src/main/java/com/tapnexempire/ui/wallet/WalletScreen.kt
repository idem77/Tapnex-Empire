package com.tapnexempire.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapnexempire.viewmodel.WalletViewModel
import com.tapnexempire.utils.UiState
import com.tapnexempire.data.model.WalletModel

@Composable
fun WalletScreen(
    viewModel: WalletViewModel,
    userId: String,
    onTransactionClick: () -> Unit
) {

    val state by viewModel.walletState.collectAsState()

    LaunchedEffect(userId) {
        viewModel.loadWallet(userId)
    }

    when (state) {

        is UiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is UiState.Error -> {
            val message = (state as UiState.Error).message

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(message)
            }
        }

        is UiState.Success -> {

            val wallet = (state as UiState.Success<WalletModel>).data

            val total =
                wallet.depositCoins +
                wallet.bonusCoins +
                wallet.withdrawableCoins

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Text(
                    text = "My Wallet 💰",
                    style = MaterialTheme.typography.headlineMedium
                )

                // 💣 MAIN CARD
                Card(
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(
                            text = "Total Coins: $total",
                            style = MaterialTheme.typography.titleLarge
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text("Deposit Coins: ${wallet.depositCoins}")
                        Text("Bonus Coins: ${wallet.bonusCoins}")
                        Text("Withdrawable Coins: ${wallet.withdrawableCoins}")
                    }
                }

                // 🔥 ACTION BUTTON
                Button(
                    onClick = onTransactionClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("View Transactions 📜")
                }
            }
        }
    }
}
