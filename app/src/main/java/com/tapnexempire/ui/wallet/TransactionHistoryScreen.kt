package com.tapnexempire.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tapnexempire.data.model.TransactionModel
import com.tapnexempire.data.model.TransactionType
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TransactionHistoryScreen(
    viewModel: TransactionViewModel,
    userId: String
) {

    val state by viewModel.transactions.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadTransactions(userId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Transaction History 👑",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (state) {

            is UiState.Loading -> {
                CircularProgressIndicator()
            }

            is UiState.Error -> {
                Text("Failed to load transactions 😢")
            }

            is UiState.Success -> {

                val transactions =
                    (state as UiState.Success<List<TransactionModel>>).data

                if (transactions.isEmpty()) {

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No transactions yet 🪙")
                    }

                } else {

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(transactions) { transaction ->
                            TransactionItem(transaction)
                        }
                    }
                }
            }
        }
    }
}
