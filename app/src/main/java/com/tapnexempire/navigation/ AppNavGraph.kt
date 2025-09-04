package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tapnexempire.screens.*

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("home") {
            HomeScreen(navController)
        }
        composable("wallet") {
            WalletScreen(navController)
        }
        composable("redeem") {
            RedeemScreen(navController)
        }
        composable("tasks") {
            TaskScreen(navController)
        }
        composable("profile") {
            ProfileScreen(navController)
        }
        composable("offers") {
            OffersScreen(navController)
        }
        composable("help") {
            HelpScreen(navController)
        }
    }
}
