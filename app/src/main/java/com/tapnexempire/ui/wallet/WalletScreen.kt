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
    val depositBalance = viewModel.depositBalance.collectAsState().value
    val withdrawableBalance = viewModel.withdrawableBalance.collectAsState().value
    val referralRewards = viewModel.referralRewards.collectAsState().value
    val totalCoins = viewModel.totalCoins.collectAsState().value

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
            item {
                Text(
                    text = "Total Coins: $totalCoins",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )
                Divider()
                Spacer(modifier = Modifier.height(10.dp))
            }

            item {
                WalletBalanceCard("Deposit Balance", depositBalance)
                Spacer(modifier = Modifier.height(10.dp))
                WalletBalanceCard("Withdrawable Balance", withdrawableBalance)
                Spacer(modifier = Modifier.height(10.dp))
                WalletBalanceCard("Referral Rewards", referralRewards)
                Spacer(modifier = Modifier.height(20.dp))
            }

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
