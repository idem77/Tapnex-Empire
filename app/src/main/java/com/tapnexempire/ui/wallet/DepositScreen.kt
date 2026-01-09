package com.tapnexempire.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tapnexempire.viewmodel.WalletViewModel

@Composable
fun DepositScreen(
    navController: NavController,
    viewModel: WalletViewModel = hiltViewModel()
) {
    var coinInput by remember { mutableStateOf("") }
    val isLoading by viewModel.loading.collectAsState()
    val errorMessage by viewModel.error.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Deposit Coins",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = coinInput,
            onValueChange = { coinInput = it },
            label = { Text("Enter Coins") },
            keyboardOptions = androidx.compose.ui.text.input.KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "₹ ${(coinInput.toIntOrNull() ?: 0) * 0.10}",
            style = MaterialTheme.typography.bodyLarge
        )

        if (errorMessage != null) {
            Text(
                text = errorMessage ?: "",
                color = MaterialTheme.colorScheme.error
            )
        }

        Button(
            onClick = {
                val coins = coinInput.toIntOrNull() ?: return@Button
                viewModel.depositCoins(coins)
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
                Text("Confirm Deposit")
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
