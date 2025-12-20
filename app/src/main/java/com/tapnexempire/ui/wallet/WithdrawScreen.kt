package com.tapnexempire.ui.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.ui.theme.Gold
import com.tapnexempire.ui.theme.CharcoalBlack

data class WithdrawTransaction(
    val id: Int,
    val amount: Int,
    val status: String // "Pending", "Completed"
)

@Composable
fun WithdrawScreen(
    totalCoins: Int,
    withdrawableCoins: Int,
    onWithdraw: (amount: Int) -> Unit,
    transactions: List<WithdrawTransaction>
) {
    var amount by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Withdraw Coins",
            fontSize = 28.sp,
            color = CharcoalBlack,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text(
            text = "Total Coins: $totalCoins",
            fontSize = 18.sp,
            color = CharcoalBlack,
            modifier = Modifier.padding(vertical = 4.dp)
        )

        Text(
            text = "Withdrawable Coins: $withdrawableCoins",
            fontSize = 18.sp,
            color = Color.Green,
            modifier = Modifier.padding(vertical = 4.dp)
        )

        OutlinedTextField(
            value = amount,
            onValueChange = { value ->
                if (value.all { it.isDigit() }) amount = value
            },
            label = { Text("Enter coins to withdraw") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            singleLine = true
        )

        Button(
            onClick = {
                if (amount.isNotEmpty() && amount.toInt() <= withdrawableCoins) {
                    onWithdraw(amount.toInt())
                    amount = ""
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Gold),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(
                text = "Withdraw",
                color = CharcoalBlack,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Withdraw Transactions",
            fontSize = 20.sp,
            color = CharcoalBlack,
            modifier = Modifier.padding(vertical = 8.dp)
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
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF1F1))
                ) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "ID: ${tx.id}", fontSize = 14.sp, color = CharcoalBlack)
                        Text(text = "${tx.amount} coins", fontSize = 16.sp, color = Color.Black)
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
