package com.tapnexempire.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tapnexempire.navigation.Routes
import com.tapnexempire.viewmodel.WalletViewModel
import com.tapnexempire.data.model.WalletModel
import com.tapnexempire.utils.UiState

@Composable
fun WalletScreen(
    userId: String,
    navController: NavController,
    onTransactionClick: () -> Unit,
    viewModel: WalletViewModel = hiltViewModel()
) {

    // ✅ SAFETY CHECK
    if (userId.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("User not logged in ❌")
        }
        return
    }

    val state by viewModel.walletState.collectAsState()

    // ✅ FIXED LISTENER
    LaunchedEffect(userId) {
        viewModel.startWalletListener(userId)
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
                Text("❌ $message")
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

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text("Total Coins: $total")

                        Spacer(modifier = Modifier.height(8.dp))

                        Text("Deposit Coins: ${wallet.depositCoins}")
                        Text("Bonus Coins: ${wallet.bonusCoins}")
                        Text("Withdrawable Coins: ${wallet.withdrawableCoins}")
                    }
                }

                // 💰 Deposit Button
                Button(
                    onClick = {
                        navController.navigate(Routes.DEPOSIT)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Deposit")
                }

                // 💸 Withdraw Button
                Button(
                    onClick = {
                        navController.navigate(Routes.WITHDRAW)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Withdraw")
                }

                // 📜 Transactions
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
