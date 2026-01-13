package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tapnexempire.ui.auth.OtpLoginScreen
import com.tapnexempire.ui.auth.OtpVerificationScreen
import com.tapnexempire.ui.home.HomeScreen
import com.tapnexempire.ui.profile.ProfileScreen
import com.tapnexempire.ui.profile.SettingsScreen
import com.tapnexempire.ui.task.TaskScreen
import com.tapnexempire.ui.tournament.*
import com.tapnexempire.ui.tournament.detail.TournamentDetailScreen
import com.tapnexempire.ui.wallet.*

@Composable
fun AppNavGraph(startDestination: String = Routes.SPLASH) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        /* ---------------- AUTH ---------------- */

        composable(Routes.SPLASH) {
            SplashScreen(
                onFinished = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.LOGIN) {
            OtpLoginScreen(
                onOtpSent = { verificationId ->
                    navController.navigate(
                        Routes.OTP_VERIFY + "/$verificationId"
                    )
                }
            )
        }

        composable(
            route = Routes.OTP_VERIFY + "/{verificationId}",
            arguments = listOf(
                navArgument("verificationId") { type = NavType.StringType }
            )
        ) {
            val verificationId = it.arguments?.getString("verificationId") ?: ""
            OtpVerificationScreen(
                verificationId = verificationId,
                onSuccess = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        /* ---------------- HOME ---------------- */

        composable(Routes.HOME) {
            HomeScreen(
                onWalletClick = { navController.navigate(Routes.WALLET) },
                onTournamentClick = { navController.navigate(Routes.TOURNAMENT_LIST) },
                onTaskClick = { navController.navigate(Routes.TASKS) }
            )
        }

        /* ---------------- WALLET ---------------- */

        composable(Routes.WALLET) {
            WalletScreen(
                onDeposit = { navController.navigate(Routes.DEPOSIT) },
                onWithdraw = { navController.navigate(Routes.WITHDRAW) },
                onTransactions = { navController.navigate(Routes.TRANSACTIONS) }
            )
        }

        composable(Routes.DEPOSIT) {
            DepositScreen()
        }

        composable(Routes.WITHDRAW) {
            WithdrawScreen()
        }

        composable(Routes.TRANSACTIONS) {
            TransactionHistoryScreen()
        }

        /* ---------------- TOURNAMENT ---------------- */

        composable(Routes.TOURNAMENT_LIST) {
            TournamentListScreen(
                onTournamentClick = { tournamentId ->
                    navController.navigate(
                        Routes.TOURNAMENT_DETAIL + "/$tournamentId"
                    )
                }
            )
        }

        composable(
            route = Routes.TOURNAMENT_DETAIL + "/{tournamentId}",
            arguments = listOf(
                navArgument("tournamentId") { type = NavType.StringType }
            )
        ) {
            val tournamentId = it.arguments?.getString("tournamentId") ?: ""
            TournamentDetailScreen(
                tournamentId = tournamentId
            )
        }

        composable(Routes.MY_TOURNAMENTS) {
            MyTournamentsScreen(
                onTournamentClick = { tournamentId ->
                    navController.navigate(
                        Routes.TOURNAMENT_DETAIL + "/$tournamentId"
                    )
                }
            )
        }

        /* ---------------- TASKS ---------------- */

        composable(Routes.TASKS) {
            TaskScreen()
        }

        /* ---------------- PROFILE ---------------- */

        composable(Routes.PROFILE) {
            ProfileScreen(
                onSettingsClick = {
                    navController.navigate(Routes.SETTINGS)
                }
            )
        }

        composable(Routes.SETTINGS) {
            SettingsScreen(
                onLogout = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                }
            )
        }
    }
}
