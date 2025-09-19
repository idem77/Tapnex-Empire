package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tapnexempire.ui.auth.LoginScreen
import com.tapnexempire.ui.auth.OtpVerificationScreen
import com.tapnexempire.ui.auth.SignupScreen
import com.tapnexempire.ui.home.HomeScreen
import com.tapnexempire.ui.wallet.RechargeScreen
import com.tapnexempire.ui.wallet.WithdrawScreen
import com.tapnexempire.ui.wallet.WalletScreen
import com.tapnexempire.ui.profile.ProfileScreen
import com.tapnexempire.ui.splash.SplashScreen
import com.tapnexempire.ui.task.TaskScreen
import com.tapnexempire.ui.tournament.TournamentScreen
import com.tapnexempire.ui.tournament.TournamentListScreen
import com.tapnexempire.ui.tournament.TournamentDetailScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {

        // Splash
        composable("splash") {
            SplashScreen(navController = navController)
        }

        // Auth
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("signup") {
            SignupScreen(navController = navController)
        }
        composable(
            route = "otp_verification/{phoneNumber}",
            arguments = listOf(navArgument("phoneNumber") { type = NavType.StringType })
        ) { backStackEntry ->
            val phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: ""
            OtpVerificationScreen(navController = navController, phoneNumber = phoneNumber)
        }

        // Home
        composable("home") {
            HomeScreen(navController = navController)
        }

        // Wallet
        composable("wallet") {
            WalletScreen(navController = navController)
        }
        composable("recharge") {
            RechargeScreen(navController = navController)
        }
        composable("withdraw") {
            WithdrawScreen(navController = navController)
        }

        // Profile
        composable("profile") {
            ProfileScreen(navController = navController)
        }

        // Task
        composable("task") {
            TaskScreen(navController = navController)
        }

        // Tournament
        composable("tournament") {
            TournamentScreen(navController = navController)
        }
        composable("tournament_list") {
            TournamentListScreen(navController = navController)
        }
        composable(
            route = "tournament_detail/{tournamentId}",
            arguments = listOf(navArgument("tournamentId") { type = NavType.StringType })
        ) { backStackEntry ->
            val tournamentId = backStackEntry.arguments?.getString("tournamentId") ?: ""
            TournamentDetailScreen(navController = navController, tournamentId = tournamentId)
        }
    }
}
