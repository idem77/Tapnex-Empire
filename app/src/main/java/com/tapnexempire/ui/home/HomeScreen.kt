package com.tapnexempire.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Task
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tapnexempire.navigation.Screen

@Composable
fun HomeScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf("home") }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(selected = selectedTab == "home", onClick = {
                    selectedTab = "home"
                    navController.navigate(Screen.Home.route) { popUpTo(Screen.Home.route) { inclusive = true } }
                }, icon = { Icon(Icons.Default.Home, "Home") }, label = { Text("Home") })

                NavigationBarItem(selected = selectedTab == "wallet", onClick = {
                    selectedTab = "wallet"
                    navController.navigate(Screen.Wallet.route)
                }, icon = { Icon(Icons.Default.Wallet, "Wallet") }, label = { Text("Wallet") })

                NavigationBarItem(selected = selectedTab == "task", onClick = {
                    selectedTab = "task"
                    navController.navigate(Screen.Tasks.route)
                }, icon = { Icon(Icons.Default.Task, "Tasks") }, label = { Text("Tasks") })

                NavigationBarItem(selected = selectedTab == "tournament", onClick = {
                    selectedTab = "tournament"
                    navController.navigate(Screen.TournamentList.route)
                }, icon = { Icon(Icons.Default.AccountCircle, "Tournament") }, label = { Text("Tournaments") })
            }
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Welcome to Tapnex Empire", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(12.dp))
            // Wallet preview
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Balance: 10,000 Coins", style = MaterialTheme.typography.titleMedium)
                    Text("≈ ₹100")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            // Quick tiles can be components (GameTile / CoinCard)
            Button(onClick = { navController.navigate(Screen.TournamentList.route) }, modifier = Modifier.fillMaxWidth()) {
                Text("View Tournaments")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { navController.navigate(Screen.Ludo.route) }, modifier = Modifier.fillMaxWidth()) {
                Text("Play Ludo (Coming Soon)")
            }
        }
    }
}
