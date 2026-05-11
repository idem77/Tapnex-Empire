package com.tapnexempire.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.viewmodel.TransactionViewModel

@Composable
fun TransactionHistoryScreen(

    userId: String,

    viewModel: TransactionViewModel = hiltViewModel()
) {

    val transactions by
        viewModel.transactions.collectAsState()

    LaunchedEffect(userId) {

        viewModel.startTransactionListener(userId)
    }

    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement =
            Arrangement.spacedBy(12.dp)
    ) {

        items(transactions) { transaction ->

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = transaction.type.,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Amount: ₹${transaction.amount}"
                    )

                    Text(
                        text = transaction.description
                    )

                    Text(
                        text = "User: ${transaction.userId}"
                    )
                }
            }
        }
    }
}
