package com.tapnexempire.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tapnexempire.components.BottomNavBar

@Composable
fun HomeScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "wallet",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("wallet") { PlaceholderScreen("Wallet Screen") }
            composable("games") { PlaceholderScreen("Games Screen") }
            composable("offers") { PlaceholderScreen("Offers Screen") }
            composable("tasks") { PlaceholderScreen("Tasks Screen") }
            composable("rewards") { PlaceholderScreen("Rewards Screen") }
            composable("redeem") { PlaceholderScreen("Redeem Screen") }
            composable("profile") { PlaceholderScreen("Profile Screen") }
            composable("help") { PlaceholderScreen("Help Screen") }
        }
    }
}
