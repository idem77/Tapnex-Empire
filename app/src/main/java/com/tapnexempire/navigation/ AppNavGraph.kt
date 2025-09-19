package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tapnexempire.ui.auth.LoginScreen
import com.tapnexempire.ui.auth.OtpVerificationScreen
import com.tapnexempire.ui.home.HomeScreen
import com.tapnexempire.ui.wallet.RechargeScreen
import com.tapnexempire.ui.wallet.WithdrawScreen
import com.tapnexempire.ui.wallet.WalletScreen
import com.tapnexempire.ui.tournament.TournamentScreen
import com.tapnexempire.ui.tournament.TournamentListScreen
import com.tapnexempire.ui.tournament.TournamentDetailScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        // 🔹 Auth
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("otp") {
            OtpVerificationScreen(navController = navController)
        }

        // 🔹 Home
        composable("home") {
            HomeScreen(navController = navController)
        }

        // 🔹 Wallet
        composable("wallet") {
            WalletScreen(navController = navController)
        }
        composable("recharge") {
            RechargeScreen(navController = navController)
        }
        composable("withdraw") {
            WithdrawScreen(navController = navController)
        }

        // 🔹 Tournament
        composable("tournament") {
            TournamentScreen(navController = navController)
        }
        composable("tournament_list") {
            TournamentListScreen(navController = navController)
        }
        composable("tournament_detail") {
            TournamentDetailScreen(navController = navController)
        }
    }
}
