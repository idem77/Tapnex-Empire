package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.tapnexempire.ui.auth.*
import com.tapnexempire.ui.home.HomeScreen
import com.tapnexempire.ui.profile.*
import com.tapnexempire.ui.splash.SplashScreen
import com.tapnexempire.ui.task.TaskScreen
import com.tapnexempire.ui.tournament.*
import com.tapnexempire.ui.wallet.*

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {

        // Splash
        composable("splash") {
            SplashScreen(navController)
        }

        // Auth
        composable("login") {
            OtpLoginScreen(navController)
        }

        composable("otp") {
            OtpVerificationScreen(navController)
        }

        // Main
        composable("home") {
            HomeScreen(navController)
        }

        composable("task") {
            TaskScreen(navController)
        }

        composable("wallet") {
            WalletScreen(navController)
        }

        composable("profile") {
            ProfileScreen(navController)
        }

        // Wallet Sub
        composable("deposit") {
            DepositScreen(navController)
        }

        composable("withdraw") {
            WithdrawScreen(navController)
        }

        composable("transactions") {
            TransactionHistoryScreen(navController)
        }

        // Tournament
        composable("tournaments") {
            TournamentListScreen(navController)
        }

        composable(
            "tournament_detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            TournamentDetailScreen(navController)
        }

        composable("my_tournaments") {
            MyTournamentsScreen(navController)
        }
    }
}
