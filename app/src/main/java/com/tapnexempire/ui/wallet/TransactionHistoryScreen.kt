package com.tapnexempire.ui.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.ui.theme.CharcoalBlack
import com.tapnexempire.ui.theme.Gold

data class Transaction(
    val id: Int,
    val type: String,  // "Deposit", "Withdrawal", "Reward", "Tournament"
    val amount: Int,
    val status: String, // "Pending", "Completed"
    val date: String
)

@Composable
fun TransactionHistoryScreen(
    transactions: List<Transaction>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Transaction History",
            fontSize = 28.sp,
            color = CharcoalBlack,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(transactions) { tx ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9))
                ) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = "ID: ${tx.id}", fontSize = 14.sp, color = CharcoalBlack)
                            Text(text = tx.type, fontSize = 16.sp, color = Gold)
                            Text(text = tx.date, fontSize = 12.sp, color = Color.Gray)
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "${tx.amount} coins",
                                fontSize = 16.sp,
                                color = if (tx.amount >= 0) Color.Green else Color.Red
                            )
                            Text(
                                text = tx.status,
                                fontSize = 14.sp,
                                color = if (tx.status == "Completed") Color.Green else Color.Red
                            )
                        }
                    }
                }
            }
        }
    }
}
