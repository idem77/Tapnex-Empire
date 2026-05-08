package com.tapnexempire.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.data.model.TransactionModel
import kotlinx.coroutines.tasks.await

@Composable
fun TransactionHistoryScreen(
    userId: String
) {

    var transactions by remember {
        mutableStateOf<List<TransactionModel>>(emptyList())
    }

    var isLoading by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {

        try {

            val snapshot = FirebaseFirestore.getInstance()
                .collection("transactions")
                .document(userId)
                .collection("history")
                .orderBy("createdAt")
                .get()
                .await()

            transactions =
                snapshot.toObjects(TransactionModel::class.java)
                    .reversed()

        } catch (e: Exception) {
            e.printStackTrace()
        }

        isLoading = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Transaction History 📜",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {

            CircularProgressIndicator()

        } else if (transactions.isEmpty()) {

            Text("No transactions found")

        } else {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(transactions) { transaction ->

                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = "Type: ${transaction.type}"
                            )

                            Text(
                                text = "Coins: ${transaction.coins}"
                            )

                            Text(
                                text = "Amount: ₹${transaction.amount}"
                            )

                            Text(
                                text = "Status: ${transaction.status}"
                            )

                            Text(
                                text = transaction.description
                            )
                        }
                    }
                }
            }
        }
    }
}
