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
import com.tapnexempire.ui.wallet.WalletScreen
import com.tapnexempire.ui.profile.ProfileScreen
import com.tapnexempire.ui.splash.SplashScreen
import com.tapnexempire.ui.task.TaskScreen
import com.tapnexempire.ui.tournament.TournamentScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        // 🔹 Splash Screen
        composable("splash") {
            SplashScreen(navController = navController)
        }

        // 🔹 Login Screen
        composable("login") {
            LoginScreen(navController = navController)
        }

        // 🔹 Signup Screen
        composable("signup") {
            SignupScreen(navController = navController)
        }

        // 🔹 OTP Verification with phoneNumber argument
        composable(
            route = "otp_verification/{phoneNumber}",
            arguments = listOf(navArgument("phoneNumber") { type = NavType.StringType })
        ) { backStackEntry ->
            val phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: ""
            OtpVerificationScreen(navController = navController, phoneNumber = phoneNumber)
        }

        // 🔹 Home
        composable("home") {
            HomeScreen(navController = navController)
        }

        // 🔹 Wallet
        composable("wallet") {
            WalletScreen(navController = navController)
        }

        // 🔹 Profile
        composable("profile") {
            ProfileScreen(navController = navController)
        }

        // 🔹 Task
        composable("task") {
            TaskScreen(navController = navController)
        }

        // 🔹 Tournament
        composable("tournament") {
            TournamentScreen(navController = navController)
        }
    }
}
