package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tapnexempire.ui.auth.AuthScreen
import com.tapnexempire.ui.auth.OtpScreen
import com.tapnexempire.ui.splash.SplashScreen
import com.tapnexempire.ui.home.HomeScreen
import com.tapnexempire.ui.wallet.WalletScreen
import com.tapnexempire.ui.wallet.RechargeScreen
import com.tapnexempire.ui.wallet.WithdrawScreen
import com.tapnexempire.ui.wallet.TransactionHistoryScreen
import com.tapnexempire.ui.task.TaskScreen
import com.tapnexempire.ui.tournament.TournamentScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(Screen.Auth.route) {
            AuthScreen(navController)
        }
        composable(Screen.Otp.route) {
            OtpScreen(navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Wallet.route) {
            WalletScreen(navController)
        }
        composable(Screen.Recharge.route) {
            RechargeScreen(navController)
        }
        composable(Screen.Withdraw.route) {
            WithdrawScreen(navController)
        }
        composable(Screen.TransactionHistory.route) {
            TransactionHistoryScreen(navController)
        }
        composable(Screen.Task.route) {
            TaskScreen(navController)
        }
        composable(Screen.Tournament.route) {
            TournamentScreen(navController)
        }
    }
}
