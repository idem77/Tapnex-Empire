package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tapnexempire.screen.AuthScreen
import com.tapnexempire.screen.HomeScreen
import com.tapnexempire.screen.HelpScreen
import com.tapnexempire.screen.PlaceholderScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "auth"
    ) {
        composable("auth") {
            AuthScreen(navController)
        }
        composable("home") {
            HomeScreen(navController)
        }
        composable("help") {
            HelpScreen(navController)
        }
        composable("profile") {
            PlaceholderScreen("Profile Screen")
        }
        composable("wallet") {
            PlaceholderScreen("Wallet Screen")
        }
        composable("redeem") {
            PlaceholderScreen("Redeem Screen")
        }
    }
}
