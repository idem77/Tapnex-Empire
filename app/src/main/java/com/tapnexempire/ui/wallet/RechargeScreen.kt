package com.tapnexempire.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tapnexempire.navigation.Screen

@Composable
fun RechargeScreen(navController: NavController) {
    var selected by remember { mutableStateOf(10) }
    val options = listOf(10, 30, 50, 100, 200, 500)
    Scaffold(topBar = { CenterAlignedTopAppBar(title = { Text("Recharge") }) }) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            Text("Select amount")
            Spacer(Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                options.forEach { amount ->
                    FilterChip(selected = selected == amount, onClick = { selected = amount }, label = { Text("₹$amount") })
                }
            }
            Spacer(Modifier.height(20.dp))
            val coins = selected * 100
            Text("You will receive $coins Coins")
            Spacer(Modifier.height(20.dp))
            Button(onClick = {
                // TODO: Integrate payment gateway (Razorpay/UPI)
            }, modifier = Modifier.fillMaxWidth()) { Text("Pay ₹$selected") }
            Spacer(Modifier.height(8.dp))
            OutlinedButton(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth()) { Text("Cancel") }
        }
    }
}
