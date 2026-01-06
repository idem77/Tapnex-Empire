package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tapnexempire.ui.auth.OtpLoginScreen
import com.tapnexempire.ui.auth.OtpVerificationScreen
import com.tapnexempire.ui.home.HomeScreen
import com.tapnexempire.ui.profile.ProfileScreen
import com.tapnexempire.ui.profile.SettingsScreen
import com.tapnexempire.ui.splash.SplashScreen
import com.tapnexempire.ui.task.TaskScreen
import com.tapnexempire.ui.tournament.MyTournamentsScreen
import com.tapnexempire.ui.tournament.TournamentListScreen
import com.tapnexempire.ui.wallet.WalletScreen
import com.tapnexempire.ui.wallet.WithdrawScreen

object Routes {
    const val SPLASH = "splash"
    const val LOGIN = "login"
    const val OTP = "otp"
    const val HOME = "home"
    const val WALLET = "wallet"
    const val WITHDRAW = "withdraw"
    const val TOURNAMENTS = "tournaments"
    const val MY_TOURNAMENTS = "my_tournaments"
    const val TASKS = "tasks"
    const val PROFILE = "profile"
    const val SETTINGS = "settings"
}

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {

        composable(Routes.SPLASH) {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.LOGIN) {
            OtpLoginScreen(
                onOtpSent = { verificationId ->
                    navController.navigate("${Routes.OTP}/$verificationId")
                }
            )
        }

        composable("${Routes.OTP}/{verificationId}") {
            OtpVerificationScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.HOME) {
            HomeScreen(
                onWalletClick = { navController.navigate(Routes.WALLET) },
                onTournamentClick = { navController.navigate(Routes.TOURNAMENTS) },
                onTaskClick = { navController.navigate(Routes.TASKS) },
                onProfileClick = { navController.navigate(Routes.PROFILE) }
            )
        }

        composable(Routes.WALLET) {
            WalletScreen(
                onWithdrawClick = { navController.navigate(Routes.WITHDRAW) }
            )
        }

        composable(Routes.WITHDRAW) {
            WithdrawScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.TOURNAMENTS) {
            TournamentListScreen(
                onMyTournamentsClick = {
                    navController.navigate(Routes.MY_TOURNAMENTS)
                }
            )
        }

        composable(Routes.MY_TOURNAMENTS) {
            MyTournamentsScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.TASKS) {
            TaskScreen()
        }

        composable(Routes.PROFILE) {
            ProfileScreen(
                onSettingsClick = {
                    navController.navigate(Routes.SETTINGS)
                }
            )
        }

        composable(Routes.SETTINGS) {
            SettingsScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
