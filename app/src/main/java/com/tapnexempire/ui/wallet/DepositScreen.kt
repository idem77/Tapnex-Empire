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
    viewModel: WalletViewModel = hiltViewModel()
) {
    var amount by remember { mutableStateOf("") }
    val state by viewModel.depositState.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Enter Amount") }
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { viewModel.depositCoins(amount.toIntOrNull() ?: 0) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Deposit")
        }

        state.error?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }
    }
}
