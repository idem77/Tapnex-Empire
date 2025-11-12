package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tapnexempire.ui.auth.LoginScreen
import com.tapnexempire.ui.auth.OtpLoginScreen
import com.tapnexempire.ui.auth.OtpVerificationScreen
import com.tapnexempire.ui.home.HomeScreen
import com.tapnexempire.ui.splash.SplashScreen
import com.tapnexempire.ui.wallet.WalletScreen
import com.tapnexempire.ui.wallet.DepositScreen
import com.tapnexempire.ui.wallet.WithdrawScreen
import com.tapnexempire.ui.wallet.TransactionHistoryScreen
import com.tapnexempire.ui.task.TaskScreen
import com.tapnexempire.ui.tournament.TournamentScreen
import com.tapnexempire.ui.tournament.TournamentDetailScreen
import com.tapnexempire.viewmodel.AuthViewModel
import com.tapnexempire.viewmodel.WalletViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    walletViewModel: WalletViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(onTimeout = {
                navController.navigate("login") {
                    popUpTo("splash") { inclusive = true }
                }
            })
        }

        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                authViewModel = authViewModel
            )
        }

        composable("otp_login") {
            OtpLoginScreen(
                onOtpSent = { navController.navigate("otp_verification") },
                authViewModel = authViewModel
            )
        }

        composable("otp_verification") {
            OtpVerificationScreen(
                onVerified = {
                    navController.navigate("home") {
                        popUpTo("otp_verification") { inclusive = true }
                    }
                },
                authViewModel = authViewModel
            )
        }

        composable("home") {
            HomeScreen(
                onWalletClick = { navController.navigate("wallet") },
                onTaskClick = { navController.navigate("tasks") },
                onTournamentClick = { navController.navigate("tournaments") },
            )
        }

        composable("wallet") {
            WalletScreen(
                coins = walletViewModel.walletBalance,
                onDepositClick = { navController.navigate("deposit") },
                onWithdrawClick = { navController.navigate("withdraw") },
                onTransactionHistoryClick = { navController.navigate("transactions") },
                viewModel = walletViewModel
            )
        }

        composable("deposit") {
            DepositScreen(onBackClick = { navController.popBackStack() }, viewModel = walletViewModel)
        }

        composable("withdraw") {
            WithdrawScreen(onBackClick = { navController.popBackStack() }, viewModel = walletViewModel)
        }

        composable("transactions") {
            TransactionHistoryScreen(onBackClick = { navController.popBackStack() }, viewModel = walletViewModel)
        }

        composable("tasks") {
            TaskScreen(onBackClick = { navController.popBackStack() })
        }

        composable("tournaments") {
            TournamentScreen(onTournamentClick = { id ->
                navController.navigate("tournament_detail/$id")
            })
        }

        composable("tournament_detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            TournamentDetailScreen(id = id, onBackClick = { navController.popBackStack() })
        }
    }
}
