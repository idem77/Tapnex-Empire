package com.tapnexempire.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun WithdrawScreen(navController: NavController) {
    var amount by remember { mutableStateOf(TextFieldValue("")) }
    var method by remember { mutableStateOf("UPI") }
    Scaffold(topBar = { CenterAlignedTopAppBar(title = { Text("Withdraw") }) }) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            Text("Available Winnings: 1200 Coins (≈ ₹12)")
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(value = amount, onValueChange = { amount = it }, label = { Text("Enter amount in ₹") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                FilterChip(selected = method == "UPI", onClick = { method = "UPI" }, label = { Text("UPI") })
                FilterChip(selected = method == "Gift Card", onClick = { method = "Gift Card" }, label = { Text("Gift Card") })
            }
            Spacer(Modifier.height(12.dp))
            Button(onClick = {
                // TODO: Validate, create withdraw request in backend
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Request Withdraw")
            }
            Spacer(Modifier.height(8.dp))
            OutlinedButton(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth()) {
                Text("Cancel")
            }
        }
    }
}
