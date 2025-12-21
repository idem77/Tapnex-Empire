package com.tapnexempire.ui.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.components.CoinCard
import com.tapnexempire.ui.theme.CharcoalBlack
import com.tapnexempire.ui.theme.Gold
import com.tapnexempire.ui.theme.LightCream
import com.tapnexempire.viewmodel.WalletViewModel

@Composable
fun WalletScreen(
    onDepositClick: () -> Unit,
    onWithdrawClick: () -> Unit,
    onDailyBonusClick: () -> Unit,
    viewModel: WalletViewModel = hiltViewModel()
) {
    val walletState by viewModel.walletState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightCream)
            .padding(16.dp)
    ) {
        // Coins Card
        CoinCard(
            coins = walletState.totalCoins,
            onClick = { onDepositClick() },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Action Buttons Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            WalletActionButton("Deposit") { onDepositClick() }
            WalletActionButton("Withdraw") { onWithdrawClick() }
            WalletActionButton("Daily Bonus") { onDailyBonusClick() }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Transactions List
        Text(
            text = "Transaction History",
            fontSize = 20.sp,
            color = CharcoalBlack,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(walletState.transactions) { transaction ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(LightCream)
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = transaction.title,
                            color = CharcoalBlack,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "ID: ${transaction.id}",
                            color = CharcoalBlack.copy(alpha = 0.6f),
                            fontSize = 12.sp
                        )
                    }
                    Text(
                        text = if (transaction.isCredit)
                            "+${transaction.amount}"
                        else
                            "-${transaction.amount}",
                        color = if (transaction.isCredit) Gold else CharcoalBlack,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun WalletActionButton(
    title: String,
    onClick: () -> Unit
) {
    Text(
        text = title,
        color = CharcoalBlack,
        fontSize = 16.sp,
        modifier = Modifier
            .weight(1f)
            .background(Gold)
            .clickable { onClick() }
            .padding(12.dp),
    )
}
