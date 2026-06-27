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
fun WithdrawRequestScreen(
    adminViewModel: AdminViewModel = hiltViewModel()
) {

    var withdraws by remember {
        mutableStateOf(listOf<Map<String, Any>>())
    }

    var redeemCode by remember {
    mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        adminViewModel.listenWithdraws {
            withdraws = it
        }
    }

    OutlinedTextField(

    value = redeemCode,

    onValueChange = {
        redeemCode = it
    },

    label = {
        Text("Redeem Code")
    }
)

    Column(Modifier.fillMaxSize().padding(16.dp)) {

        Text("💸 Withdraw Requests", color = Color.White)

        LazyColumn {

            items(withdraws) { item ->

                val userId = item["userId"]?.toString() ?: ""
                val id = item["id"]?.toString() ?: ""
                val amount = item["amount"]?.toString()?.toLongOrNull() ?: 0L

                Card(
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {

                    Column(Modifier.padding(12.dp)) {

                        Text("User: $userId")

                        Row {

                            Button(onClick = {
                                adminViewModel.approveWithdraw(userId, id, amount, redeemCode)
                            }) {
                                Text("Approve")
                            }

                            Spacer(Modifier.width(8.dp))

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
}
