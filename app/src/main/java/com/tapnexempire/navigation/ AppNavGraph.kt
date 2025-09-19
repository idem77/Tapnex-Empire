package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tapnexempire.ui.auth.LoginScreen
import com.tapnexempire.ui.auth.OtpVerificationScreen
import com.tapnexempire.ui.home.HomeScreen
import com.tapnexempire.ui.profile.ProfileScreen
import com.tapnexempire.ui.splash.SplashScreen
import com.tapnexempire.ui.tournament.TournamentListScreen
import com.tapnexempire.ui.tournament.TournamentDetailScreen
import com.tapnexempire.ui.wallet.RechargeScreen
import com.tapnexempire.ui.wallet.WalletScreen
import com.tapnexempire.ui.wallet.WithdrawScreen
import com.tapnexempire.ui.wallet.TransactionHistoryScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        // Splash
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }

        // Auth
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.Otp.route) {
            OtpVerificationScreen(navController)
        }

        // Home
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        // Profile
        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }

        // Tournaments
        composable(Screen.Tournaments.route) {
            TournamentListScreen(navController)
        }
        composable("${Screen.TournamentDetail.route}/{tournamentId}") { backStackEntry ->
            val tournamentId = backStackEntry.arguments?.getString("tournamentId") ?: ""
            TournamentDetailScreen(navController, tournamentId)
        }

        // Wallet
        composable(Screen.Wallet.route) {
            WalletScreen(navController)
        }
        composable(Screen.Recharge.route) {
            RechargeScreen(navController)
        }
        composable(Screen.Withdraw.route) {
            WithdrawScreen(navController)
        }
        composable(Screen.History.route) {
            TransactionHistoryScreen(navController)
        }
    }
}
