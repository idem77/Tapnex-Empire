package com.tapnexempire.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tapnexempire.viewmodel.WalletViewModel

@Composable
fun WalletScreen(
    onDeposit: () -> Unit,
    onWithdraw: () -> Unit,
    onTransactions: () -> Unit,
    viewModel: WalletViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Total Coins: ${state.totalCoins}",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(24.dp))

        Button(onClick = onDeposit, modifier = Modifier.fillMaxWidth()) {
            Text("Deposit")
        }

        Spacer(Modifier.height(8.dp))

        Button(onClick = onWithdraw, modifier = Modifier.fillMaxWidth()) {
            Text("Withdraw")
        }

        Spacer(Modifier.height(8.dp))

        Button(onClick = onTransactions, modifier = Modifier.fillMaxWidth()) {
            Text("Transaction History")
        }
    }
}
