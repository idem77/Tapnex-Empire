package com.tapnexempire.ui.withdraw

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.viewmodel.WithdrawState
import com.tapnexempire.viewmodel.WithdrawViewModel

@Composable
fun WithdrawScreen(
    viewModel: WithdrawViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var amount by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Withdraw Coins 👑",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Enter coins (Max 2000)") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                amount.toIntOrNull()?.let {
                    viewModel.withdraw(it)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = uiState !is WithdrawState.Loading
        ) {
            Text("Withdraw")
        }

        when (uiState) {
            is WithdrawState.Loading -> {
                CircularProgressIndicator()
            }

            is WithdrawState.Success -> {
                Text(
                    text = "Withdraw request submitted ✅",
                    color = MaterialTheme.colorScheme.primary
                )
            }

            is WithdrawState.Error -> {
                Text(
                    text = (uiState as WithdrawState.Error).message,
                    color = MaterialTheme.colorScheme.error
                )
            }

            WithdrawState.Idle -> Unit
        }
    }
}
