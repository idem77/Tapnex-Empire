package com.tapnexempire.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tapnexempire.components.CoinCard
import com.tapnexempire.viewmodel.WalletViewModel

@Composable
fun WalletScreen(
    navController: NavController,
    viewModel: WalletViewModel = hiltViewModel()
) {
    val walletState by viewModel.wallet.collectAsState()
    val isLoading by viewModel.loading.collectAsState()
    val errorMessage by viewModel.error.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            errorMessage != null -> {
                Text(
                    text = errorMessage ?: "Something went wrong",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Text(
                        text = "My Wallet",
                        style = MaterialTheme.typography.headlineMedium
                    )

                    CoinCard(
                        title = "Total Coins",
                        coins = walletState.totalCoins
                    )

                    CoinCard(
                        title = "Withdrawable Coins",
                        coins = walletState.withdrawableCoins
                    )

                    CoinCard(
                        title = "Bonus Coins",
                        coins = walletState.bonusCoins
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            navController.navigate("deposit")
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Deposit Coins")
                    }

                    Button(
                        onClick = {
                            navController.navigate("withdraw")
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Withdraw")
                    }

                    OutlinedButton(
                        onClick = {
                            navController.navigate("transactions")
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Transaction History")
                    }
                }
            }
        }
    }
}
