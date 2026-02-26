package com.tapnexempire.ui.wallet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tapnexempire.R
import com.tapnexempire.viewmodel.WalletViewModel

@Composable
fun WalletScreen(
    viewModel: WalletViewModel,
    onDepositClick: () -> Unit,
    onWithdrawClick: () -> Unit,
    onTransactionClick: () -> Unit
) {
    val wallet by viewModel.walletState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // 🔥 BACKGROUND
        Image(
            painter = painterResource(id = R.drawable.wallet_bg), // ensure this png exists
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        if (wallet == null) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

        } else {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 70.dp) // bottom nav safe space
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "My Wallet 👑",
                    style = MaterialTheme.typography.headlineMedium
                )

                WalletBalanceCard(
                    total = wallet!!.totalEarnings,
                    bonus = wallet!!.bonusCoins,
                    deposit = wallet!!.depositCoins,
                    withdrawable = wallet!!.withdrawableCoins
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
                        enabled = wallet!!.withdrawableCoins > 0
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

                Spacer(modifier = Modifier.height(20.dp))
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
        elevation = CardDefaults.cardElevation(6.dp)
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
