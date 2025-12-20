package com.tapnexempire.ui.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.components.CoinCard
import com.tapnexempire.ui.theme.Gold
import com.tapnexempire.ui.theme.LightCream
import com.tapnexempire.ui.theme.CharcoalBlack

data class Transaction(
    val id: Int,
    val title: String,
    val amount: Int,
    val type: String // "credit" or "debit"
)

@Composable
fun WalletScreen(
    totalCoins: Int,
    transactions: List<Transaction>,
    onDepositClick: () -> Unit,
    onWithdrawClick: () -> Unit,
    onDailyBonusClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightCream)
            .padding(16.dp)
    ) {
        // Coins Card
        CoinCard(
            coins = totalCoins,
            onClick = { /* maybe open deposit screen */ },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Action Buttons Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Deposit",
                color = CharcoalBlack,
                fontSize = 16.sp,
                modifier = Modifier
                    .weight(1f)
                    .background(Gold)
                    .clickable { onDepositClick() }
                    .padding(12.dp),
            )
            Text(
                text = "Withdraw",
                color = CharcoalBlack,
                fontSize = 16.sp,
                modifier = Modifier
                    .weight(1f)
                    .background(Gold)
                    .clickable { onWithdrawClick() }
                    .padding(12.dp),
            )
            Text(
                text = "Daily Bonus",
                color = CharcoalBlack,
                fontSize = 16.sp,
                modifier = Modifier
                    .weight(1f)
                    .background(Gold)
                    .clickable { onDailyBonusClick() }
                    .padding(12.dp),
            )
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
            items(transactions) { transaction ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(LightCream)
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = transaction.title, color = CharcoalBlack, fontSize = 16.sp)
                        Text(text = "ID: ${transaction.id}", color = CharcoalBlack.copy(alpha = 0.6f), fontSize = 12.sp)
                    }
                    Text(
                        text = if (transaction.type == "credit") "+${transaction.amount}" else "-${transaction.amount}",
                        color = if (transaction.type == "credit") Gold else CharcoalBlack,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}
