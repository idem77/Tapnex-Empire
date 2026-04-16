package com.tapnexempire.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp 
import com.tapnexempire.utils.UiState
import androidx.compose.runtime.collectAsState
import com.tapnexempire.viewmodel.WalletViewModel
import com.tapnexempire.data.model.WalletModel

@Composable
fun WalletScreen(
    viewModel: WalletViewModel,
    userId: String,
    onTransactionClick: () -> Unit
) {
    val state by viewModel.walletState.collectAsState()

    LaunchedEffect(Unit) { viewModel.startWalletListener(userId) }

    when (state) {
        is UiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is UiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error loading wallet 😢")
            }
        }
        is UiState.Success -> {
            val wallet = (state as UiState.Success<WalletModel>).data
            val total = wallet.depositCoins + wallet.bonusCoins + wallet.withdrawableCoins
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("My Wallet 💰", style = MaterialTheme.typography.headlineMedium)
                Card { Column(modifier = Modifier.padding(16.dp)) {
                    Text("Total Coins: $total")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Deposit: ${wallet.depositCoins}")
                    Text("Bonus: ${wallet.bonusCoins}")
                    Text("Withdrawable: ${wallet.withdrawableCoins}")
                } }
                Button(onClick = onTransactionClick, modifier = Modifier.fillMaxWidth()) {
                    Text("View Transactions 📜")
                }
            }
        }
    }
}
