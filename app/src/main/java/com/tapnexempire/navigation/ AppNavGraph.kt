package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tapnexempire.screens.HomeScreen
import com.tapnexempire.screens.WalletScreen
import com.tapnexempire.screens.RedeemScreen
import com.tapnexempire.screens.TaskScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Wallet : Screen("wallet")
    object Redeem : Screen("redeem")
    object Task : Screen("task")
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Wallet.route) { WalletScreen(navController) }
        composable(Screen.Redeem.route) { RedeemScreen(navController) }
        composable(Screen.Task.route) { TaskScreen(navController) }
    }
}
