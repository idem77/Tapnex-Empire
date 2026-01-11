package com.tapnexempire.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tapnexempire.viewmodel.WalletViewModel

@Composable
fun TransactionHistoryScreen(
    viewModel: WalletViewModel = hiltViewModel()
) {
    val state by viewModel.transactionState.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(state.transactions) { txn ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(txn.title)
                    Text(txn.date)
                    Text(
                        text = if (txn.isCredit) "+${txn.amount}" else "-${txn.amount}",
                        color = if (txn.isCredit) Color.Green else Color.Red
                    )
                }
            }
        }
    }
}
