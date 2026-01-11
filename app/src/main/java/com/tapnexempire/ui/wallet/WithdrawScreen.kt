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
    viewModel: WithdrawViewModel = hiltViewModel()
) {
    var amount by remember { mutableStateOf("") }
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Withdraw Amount") }
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.requestWithdraw(amount.toIntOrNull() ?: 0)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Withdraw")
        }

        state.error?.let {
            Spacer(Modifier.height(8.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        state.message?.let {
            Spacer(Modifier.height(8.dp))
            Text(it)
        }
    }
}
