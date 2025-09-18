package com.tapnexempire.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletScreen(navController: NavController) {
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Wallet") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Current Balance: 1000 coins", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(16.dp))

            Button(onClick = { navController.navigate("Recharge") }, modifier = Modifier.fillMaxWidth()) {
                Text("Recharge Wallet")
            }
            Spacer(Modifier.height(8.dp))
            Button(onClick = { navController.navigate("Withdraw") }, modifier = Modifier.fillMaxWidth()) {
                Text("Withdraw Coins")
            }
            Spacer(Modifier.height(16.dp))
            
            // Temporary History placeholder
            Text("Transaction History", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            Text("No transactions yet") // temporary until real History Composable is ready
        }
    }
}
