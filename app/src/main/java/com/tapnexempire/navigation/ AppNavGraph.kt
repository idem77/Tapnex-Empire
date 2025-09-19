package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tapnexempire.ui.auth.LoginScreen
import com.tapnexempire.ui.auth.OtpVerificationScreen
import com.tapnexempire.ui.home.HomeScreen
import com.tapnexempire.ui.tournament.TournamentListScreen
import com.tapnexempire.ui.tournament.TournamentDetailScreen
import com.tapnexempire.ui.wallet.WalletScreen
import com.tapnexempire.ui.wallet.RechargeScreen
import com.tapnexempire.ui.wallet.WithdrawScreen
import com.tapnexempire.ui.history.HistoryScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        // Login Screen
        composable("login") {
            LoginScreen(
                onLoginSuccess = { phoneNumber ->
                    navController.navigate("otp/$phoneNumber")
                }
            )
        }

        // OTP Verification
        composable("otp/{phone}") { backStackEntry ->
            val phone = backStackEntry.arguments?.getString("phone") ?: ""
            OtpVerificationScreen(
                phoneNumber = phone,
                onOtpVerified = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        // Home
        composable("home") {
            HomeScreen(navController)
        }

        // Tournament List
        composable("tournaments") {
            TournamentListScreen(navController)
        }

        // Tournament Detail
        composable("tournamentDetail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            TournamentDetailScreen(tournamentId = id)
        }

        // Wallet
        composable("wallet") {
            WalletScreen(navController)
        }

        // Recharge
        composable("recharge") {
            RechargeScreen(navController)
        }

        // Withdraw
        composable("withdraw") {
            WithdrawScreen(navController)
        }

        // History
        composable("history") {
            HistoryScreen(navController)
        }
    }
}
