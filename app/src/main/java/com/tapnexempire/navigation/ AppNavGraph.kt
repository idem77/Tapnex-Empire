package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.tapnexempire.ui.splash.SplashScreen
import com.tapnexempire.ui.auth.OtpLoginScreen
import com.tapnexempire.ui.auth.OtpVerificationScreen
import com.tapnexempire.ui.home.HomeScreen

import com.tapnexempire.ui.wallet.WalletScreen
import com.tapnexempire.ui.wallet.DepositScreen
import com.tapnexempire.ui.wallet.WithdrawScreen
import com.tapnexempire.ui.wallet.TransactionHistoryScreen

import com.tapnexempire.ui.tournament.TournamentListScreen
import com.tapnexempire.ui.tournament.MyTournamentsScreen
import com.tapnexempire.ui.tournament.TournamentDetailScreen

import com.tapnexempire.ui.profile.ProfileScreen
import com.tapnexempire.ui.profile.EditProfileScreen
import com.tapnexempire.ui.profile.SettingsScreen

import com.tapnexempire.ui.task.TaskScreen

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {

        /* ---------------- SPLASH ---------------- */

        composable(Routes.SPLASH) {
            SplashScreen(
                onNavigateToHome = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                },
                onNavigateToAuth = {
                    navController.navigate(Routes.AUTH_LOGIN) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                }
            )
        }

        /* ---------------- AUTH ---------------- */

        composable(Routes.AUTH_LOGIN) {
            OtpLoginScreen(
                onOtpSent = { verificationId, phone ->
                    navController.navigate(
                        Routes.AUTH_VERIFY
                            .replace("{verificationId}", verificationId)
                            .replace("{phone}", phone)
                    )
                }
            )
        }

        composable(
            route = Routes.AUTH_VERIFY,
            arguments = listOf(
                navArgument("verificationId") { type = NavType.StringType },
                navArgument("phone") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val verificationId =
                backStackEntry.arguments?.getString("verificationId") ?: ""
            val phone =
                backStackEntry.arguments?.getString("phone") ?: ""

            OtpVerificationScreen(
                verificationId = verificationId,
                phoneNumber = phone,
                onVerified = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.AUTH_LOGIN) { inclusive = true }
                    }
                }
            )
        }

        /* ---------------- HOME ---------------- */

        composable(Routes.HOME) {
            HomeScreen(
                onWalletClick = { navController.navigate(Routes.WALLET) },
                onTournamentClick = { navController.navigate(Routes.TOURNAMENT_LIST) },
                onTaskClick = { navController.navigate(Routes.TASKS) },
                onProfileClick = { navController.navigate(Routes.PROFILE) }
            )
        }

        /* ---------------- WALLET ---------------- */

        composable(Routes.WALLET) {
            WalletScreen(
                onDepositClick = { navController.navigate(Routes.DEPOSIT) },
                onWithdrawClick = { navController.navigate(Routes.WITHDRAW) },
                onTransactionClick = { navController.navigate(Routes.TRANSACTIONS) }
            )
        }

        composable(Routes.DEPOSIT) {
            DepositScreen(onBack = { navController.popBackStack() })
        }

        composable(Routes.WITHDRAW) {
            WithdrawScreen(onBack = { navController.popBackStack() })
        }

        composable(Routes.TRANSACTIONS) {
            TransactionHistoryScreen(onBack = { navController.popBackStack() })
        }

        /* ---------------- TOURNAMENT ---------------- */

        composable(Routes.TOURNAMENT_LIST) {
            TournamentListScreen(
                onTournamentClick = { id ->
                    navController.navigate(
                        Routes.TOURNAMENT_DETAIL.replace("{tournamentId}", id)
                    )
                }
            )
        }

        composable(Routes.MY_TOURNAMENTS) {
            MyTournamentsScreen(
                onTournamentClick = { id ->
                    navController.navigate(
                        Routes.TOURNAMENT_DETAIL.replace("{tournamentId}", id)
                    )
                }
            )
        }

        composable(
            route = Routes.TOURNAMENT_DETAIL,
            arguments = listOf(
                navArgument("tournamentId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val tournamentId =
                backStackEntry.arguments?.getString("tournamentId") ?: ""

            TournamentDetailScreen(tournamentId = tournamentId)
        }

        /* ---------------- TASKS ---------------- */

        composable(Routes.TASKS) {
            TaskScreen()
        }

        /* ---------------- PROFILE ---------------- */

        composable(Routes.PROFILE) {
            ProfileScreen(
                onEditProfile = { navController.navigate(Routes.EDIT_PROFILE) },
                onSettings = { navController.navigate(Routes.SETTINGS) }
            )
        }

        composable(Routes.EDIT_PROFILE) {
            EditProfileScreen(onBack = { navController.popBackStack() })
        }

        composable(Routes.SETTINGS) {
            SettingsScreen(onBack = { navController.popBackStack() })
        }
    }
}
