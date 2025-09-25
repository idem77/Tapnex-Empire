package com.tapnexempire.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.components.AppButton
import com.tapnexempire.ui.theme.CharcoalBlack

@Composable
fun DepositScreen(
    onDepositClick: (Int) -> Unit,
    currentDepositBalance: Int
) {
    var amount by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Deposit Coins",
                fontSize = 24.sp,
                color = CharcoalBlack
            )

            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Enter amount") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            AppButton(
                text = "Deposit",
                onClick = { 
                    val amt = amount.toIntOrNull() ?: 0
                    onDepositClick(amt)
                }
            )

            Text(
                text = "Current Deposit Balance: $currentDepositBalance",
                fontSize = 16.sp,
                color = CharcoalBlack
            )
        }
    }
}
