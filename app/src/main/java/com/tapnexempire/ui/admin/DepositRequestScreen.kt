package com.tapnexempire.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.viewmodel.AdminViewModel

@Composable
fun DepositRequestScreen(
    adminViewModel: AdminViewModel = hiltViewModel()
) {

    var deposits by remember {
        mutableStateOf(listOf<Map<String, Any>>())
    }

    LaunchedEffect(Unit) {
        adminViewModel.listenDeposits {
            deposits = it
        }
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {

        Text("💰 Deposits", color = Color.White)

        LazyColumn {

            items(deposits) { item ->

                val userId = item["userId"]?.toString() ?: ""
                val id = item["id"]?.toString() ?: ""
                val amount =  (item["amountCoins"] as? Long) ?: 0L

                Card(
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {

                    Column(Modifier.padding(12.dp)) {

                        Text("User: $userId")

                        Row {

                            Button(onClick = {
                                adminViewModel.approveDeposit(userId, id, amount)
                            }) {
                                Text("Approve")
                            }

                            Spacer(Modifier.width(8.dp))

                            Button(onClick = {
                                adminViewModel.rejectDeposit(id)
                            }) {
                                Text("Reject")
                            }
                        }
                    }
                }
            }
        }
    }
}
