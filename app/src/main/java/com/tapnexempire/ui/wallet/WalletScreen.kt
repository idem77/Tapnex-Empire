package com.tapnexempire.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tapnexempire.navigation.Screen

@Composable
fun WalletScreen(navController: NavController) {
    var depositBalance by remember { mutableStateOf(2500) }
    var winningsBalance by remember { mutableStateOf(1200) }
    var adCoins by remember { mutableStateOf(300) }
    val total = depositBalance + winningsBalance + adCoins

    Scaffold(topBar = { CenterAlignedTopAppBar(title = { Text("My Wallet") }) }) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Total: $total Coins")
                    Text("Deposit: $depositBalance (Recharge Only)")
                    Text("Winnings: $winningsBalance (Withdraw Allowed)")
                    Text("Ad Coins: $adCoins (Play Only)")
                }
            }
            Spacer(Modifier.height(16.dp))
            Button(onClick = { navController.navigate(Screen.Recharge.route) }, modifier = Modifier.fillMaxWidth()) { Text("Recharge") }
            Spacer(Modifier.height(8.dp))
            OutlinedButton(onClick = { navController.navigate(Screen.Withdraw.route) }, modifier = Modifier.fillMaxWidth()) { Text("Withdraw") }
            Spacer(Modifier.height(12.dp))
            TextButton(onClick = { navController.navigate(Screen.History.route) }) { Text("Transaction History") }
        }
    }
}
