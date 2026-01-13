package com.tapnexempire.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tapnexempire.viewmodel.WalletViewModel

@Composable
fun WalletScreen(
    viewModel: WalletViewModel,
    onDepositClick: () -> Unit,
    onWithdrawClick: () -> Unit,
    onTransactionClick: () -> Unit
) {
    val wallet by viewModel.walletState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "My Wallet 👑",
            style = MaterialTheme.typography.headlineMedium
        )

        wallet?.let {

            WalletBalanceCard(
                total = it.totalEarnings,
                bonus = it.bonusCoins,
                deposit = it.depositCoins,
                withdrawable = it.withdrawableCoins
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(
                    onClick = onDepositClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Deposit")
                }

                Spacer(modifier = Modifier.width(12.dp))

                Button(
                    onClick = onWithdrawClick,
                    modifier = Modifier.weight(1f),
                    enabled = it.withdrawableCoins > 0
                ) {
                    Text("Withdraw")
                }
            }

            OutlinedButton(
                onClick = onTransactionClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Transaction History")
            }

        } ?: run {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
private fun WalletBalanceCard(
    total: Int,
    bonus: Int,
    deposit: Int,
    withdrawable: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = "Total Balance: $total coins",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )

            Divider()

            Text("Bonus Coins: $bonus")
            Text("Deposit Coins: $deposit")
            Text("Withdrawable Coins: $withdrawable")
        }
    }
}
