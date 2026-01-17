package com.tapnexempire.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun DepositScreen(
    onBack: () -> Unit,
    onProceed: (Int) -> Unit
) {
    var amount by remember { mutableStateOf("") }

    val rupees = amount.toIntOrNull() ?: 0
    val coins = rupees * 10   // 🔥 ₹1 = 10 coins (changeable later)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Deposit Coins 💰",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Enter Amount (₹)") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "You will get: $coins coins",
            style = MaterialTheme.typography.titleMedium
        )

        Button(
            onClick = { onProceed(coins) },
            modifier = Modifier.fillMaxWidth(),
            enabled = rupees > 0
        ) {
            Text("Proceed to Pay")
        }

        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}
