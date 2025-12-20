package com.tapnexempire.ui.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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

@Composable
fun DepositScreen(
    totalCoins: Int,
    onDeposit: (amount: Int) -> Unit
) {
    var amount by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Deposit Coins",
            fontSize = 28.sp,
            color = CharcoalBlack,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text(
            text = "Total Coins: $totalCoins",
            fontSize = 18.sp,
            color = CharcoalBlack,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = amount,
            onValueChange = { value ->
                if (value.all { it.isDigit() }) amount = value
            },
            label = { Text("Enter coins to deposit") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            singleLine = true,
        )

        Button(
            onClick = {
                if (amount.isNotEmpty()) onDeposit(amount.toInt())
            },
            colors = ButtonDefaults.buttonColors(containerColor = Gold),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(
                text = "Deposit",
                color = CharcoalBlack,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Note: Deposited coins cannot be withdrawn. They are only usable for tasks, tournaments, and rewards.",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}
