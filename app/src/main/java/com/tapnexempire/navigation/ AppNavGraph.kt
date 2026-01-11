package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tapnexempire.ui.auth.OtpLoginScreen
import com.tapnexempire.ui.auth.OtpVerificationScreen
import com.tapnexempire.ui.home.HomeScreen
import com.tapnexempire.ui.profile.EditProfileScreen
import com.tapnexempire.ui.profile.ProfileScreen
import com.tapnexempire.ui.profile.SettingsScreen
import com.tapnexempire.ui.splash.SplashScreen
import com.tapnexempire.ui.task.TaskScreen
import com.tapnexempire.ui.tournament.MyTournamentsScreen
import com.tapnexempire.ui.tournament.TournamentDetailScreen
import com.tapnexempire.ui.tournament.TournamentListScreen
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
        composable("otp_login") {
            OtpLoginScreen(navController)
        }

        composable(
            "otp_verify/{phone}",
            arguments = listOf(navArgument("phone") { type = NavType.StringType })
        ) {
            OtpVerificationScreen(navController)
        }

        // Home
        composable("home") {
            HomeScreen(navController)
        }

        // Tasks
        composable("tasks") {
            TaskScreen()
        }

        // Wallet
        composable("wallet") {
            WalletScreen(navController)
        }

        composable("deposit") {
            DepositScreen()
        }

        composable("withdraw") {
            WithdrawScreen()
        }

        composable("transactions") {
            TransactionHistoryScreen()
        }

        // Tournaments
        composable("tournaments") {
            TournamentListScreen(navController)
        }

        composable(
            "tournament_detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            TournamentDetailScreen(
                tournamentId = it.arguments?.getString("id") ?: ""
            )
        }

        composable("my_tournaments") {
            MyTournamentsScreen(navController)
        }

        // Profile
        composable("profile") {
            ProfileScreen(navController)
        }

        composable("edit_profile") {
            EditProfileScreen()
        }

        composable("settings") {
            SettingsScreen()
        }
    }
}
