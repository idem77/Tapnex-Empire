package com.tapnexempire.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tapnexempire.viewmodel.WithdrawViewModel

@Composable
fun WithdrawScreen(
    navController: NavController,
    viewModel: WithdrawViewModel = hiltViewModel()
) {
    var coinInput by remember { mutableStateOf("") }

    val isLoading by viewModel.loading.collectAsState()
    val message by viewModel.message.collectAsState()
    val error by viewModel.error.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Withdraw Coins",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = coinInput,
            onValueChange = { coinInput = it },
            label = { Text("Coins to Withdraw") },
            keyboardOptions = androidx.compose.ui.text.input.KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "₹ ${(coinInput.toIntOrNull() ?: 0) * 0.10}",
            style = MaterialTheme.typography.bodyLarge
        )

        if (message != null) {
            Text(
                text = message ?: "",
                color = MaterialTheme.colorScheme.primary
            )
        }

        if (error != null) {
            Text(
                text = error ?: "",
                color = MaterialTheme.colorScheme.error
            )
        }

        Button(
            onClick = {
                val coins = coinInput.toIntOrNull() ?: return@Button
                viewModel.requestWithdraw(coins)
            },
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp
                )
            } else {
                Text("Request Withdraw")
            }
        }

        OutlinedButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}
