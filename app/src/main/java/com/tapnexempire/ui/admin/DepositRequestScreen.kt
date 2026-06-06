package com.tapnexempire.ui.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tapnexempire.viewmodel.AdminViewModel
import androidx.compose.ui.unit.dp

@Composable
fun DepositRequestScreen(
    adminViewModel: AdminViewModel = hiltViewModel()
) {

    val deposits = adminViewModel.depositsLive

    Column(Modifier.fillMaxSize().padding(16.dp)) {

        Text("💰 Deposits", color = Color.White)

        deposits.forEach { item ->

            val userId = item["userId"]?.toString() ?: ""
            val id = item["id"]?.toString() ?: ""
            val amount = item["amount"]?.toString()?.toLongOrNull() ?: 0L

            Card {

                Column {

                    Text("User: $userId")

                    Row {

                        Button(onClick = {
                            adminViewModel.approveDeposit(userId, id, amount)
                        }) {
                            Text("Approve")
                        }

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
