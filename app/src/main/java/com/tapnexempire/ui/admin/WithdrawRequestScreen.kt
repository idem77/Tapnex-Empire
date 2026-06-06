package com.tapnexempire.ui.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WithdrawRequestScreen(
    adminViewModel: AdminViewModel = hiltViewModel()
) {

    val withdraws = adminViewModel.withdrawsLive

    Column(Modifier.fillMaxSize().padding(16.dp)) {

        Text("💸 Withdraw Requests", color = Color.White)

        withdraws.forEach { item ->

            val userId = item["userId"]?.toString() ?: ""
            val id = item["id"]?.toString() ?: ""
            val amount = item["amount"]?.toString()?.toLongOrNull() ?: 0L

            Card(
                Modifier.fillMaxWidth().padding(8.dp)
            ) {

                Column(Modifier.padding(12.dp)) {

                    Text("User: $userId")

                    Row {

                        Button(onClick = {
                            adminViewModel.approveWithdraw(userId, id, amount)
                        }) {
                            Text("Approve")
                        }

                        Button(onClick = {
                            adminViewModel.rejectWithdraw(id)
                        }) {
                            Text("Reject")
                        }
                    }
                }
            }
        }
    }
}
