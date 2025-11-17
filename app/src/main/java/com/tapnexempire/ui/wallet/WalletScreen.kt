package com.tapnexempire.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.viewmodel.WalletViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletScreen(
    viewModel: WalletViewModel = hiltViewModel(),
    onDepositClick: () -> Unit,
    onWithdrawClick: () -> Unit,
    onTransactionHistoryClick: () -> Unit
) {
    val uiState = viewModel.walletState.collectAsState().value

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "My Wallet 💰",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            )
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 🔥 Total Coins
            item {
                Text(
                    text = "Total Coins: ${uiState.totalCoins}",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )
                Divider()
                Spacer(modifier = Modifier.height(10.dp))
            }

            // 🔥 Deposit Coin (Bonus + Referral + Tasks)
            item {
                WalletBalanceCard("Deposit Coins", uiState.depositBalance)
                Spacer(modifier = Modifier.height(10.dp))
            }

            // 🔥 Withdrawable Coins (Only Winnings)
            item {
                WalletBalanceCard("Withdrawable Coins", uiState.withdrawableBalance)
            }

            // 🔥 Referral Rewards Separate
            item {
                WalletBalanceCard("Referral Rewards", uiState.referralRewards)
                Spacer(modifier = Modifier.height(20.dp))
            }

            // 🔘 Deposit Button
            item {
                Button(
                    onClick = onDepositClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                ) {
                    Text("Deposit Coins")
                }
            }

            // 🔘 Withdraw Button
            item {
                Button(
                    onClick = onWithdrawClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                ) {
                    Text("Withdraw Coins")
                }
            }

            // 🔘 Transaction History
            item {
                OutlinedButton(
                    onClick = onTransactionHistoryClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                ) {
                    Text("View Transaction History")
                }
            }
        }
    }
}

@Composable
fun WalletBalanceCard(title: String, amount: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "$amount Coins",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}   
