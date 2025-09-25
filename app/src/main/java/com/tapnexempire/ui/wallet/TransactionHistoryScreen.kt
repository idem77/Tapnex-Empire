package com.tapnexempire.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.ui.theme.CharcoalBlack
import com.tapnexempire.ui.theme.Gold

data class Transaction(
    val title: String,
    val amount: Int,
    val type: String // "Deposit" or "Withdraw"
)

@Composable
fun TransactionHistoryScreen(
    transactions: List<Transaction>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Transaction History",
            fontSize = 24.sp,
            color = CharcoalBlack
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(transactions) { tx ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = tx.title, fontSize = 16.sp, color = CharcoalBlack)
                    Text(
                        text = "${tx.amount} Coins",
                        fontSize = 16.sp,
                        color = if (tx.type == "Deposit") Gold else CharcoalBlack
                    )
                }
            }
        }
    }
}
