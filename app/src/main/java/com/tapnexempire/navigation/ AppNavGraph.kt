package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.firebase.auth.FirebaseAuth
import com.tapnexempire.ui.auth.OtpLoginScreen
import com.tapnexempire.ui.home.HomeScreen
import com.tapnexempire.ui.splash.SplashScreen
import com.tapnexempire.ui.task.TaskScreen
import com.tapnexempire.ui.tournament.TournamentListScreen
import com.tapnexempire.ui.tournament.TournamentDetailScreen
import com.tapnexempire.ui.wallet.WalletScreen
import com.tapnexempire.ui.withdraw.WithdrawScreen
import com.tapnexempire.viewmodel.TournamentViewModel
import com.tapnexempire.viewmodel.WalletViewModel

object Routes {
    const val SPLASH = "splash"
    const val LOGIN = "login"
    const val HOME = "home"
    const val WALLET = "wallet"
    const val TASKS = "tasks"
    const val TOURNAMENTS = "tournaments"
    const val TOURNAMENT_DETAIL = "tournament_detail"
    const val WITHDRAW = "withdraw"
}

@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {

        // 🔹 Splash
        composable(Routes.SPLASH) {
            SplashScreen(
                navController = navController,
                isLoggedIn = FirebaseAuth.getInstance().currentUser != null
            )
        }

        // 🔹 OTP Login
        composable(Routes.LOGIN) {
            OtpLoginScreen(
                onOtpSent = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        // 🔹 Home
        composable(Routes.HOME) {
            HomeScreen(
                onWalletClick = { navController.navigate(Routes.WALLET) },
                onTournamentClick = { navController.navigate(Routes.TOURNAMENTS) },
                onTaskClick = { navController.navigate(Routes.TASKS) }
            )
        }

        // 🔹 Wallet
        composable(Routes.WALLET) {
            val walletViewModel: WalletViewModel = hiltViewModel()

            WalletScreen(
                viewModel = walletViewModel,
                onDepositClick = { /* later */ },
                onWithdrawClick = { navController.navigate(Routes.WITHDRAW) },
                onTransactionClick = { /* later */ }
            )
        }

        // 🔹 Tasks
        composable(Routes.TASKS) {
            TaskScreen()
        }

        // 🔹 Tournament List
        composable(Routes.TOURNAMENTS) {
            val tournamentViewModel: TournamentViewModel = hiltViewModel()

            TournamentListScreen(
                viewModel = tournamentViewModel,
                onTournamentClick = { id ->
                    navController.navigate("${Routes.TOURNAMENT_DETAIL}/$id")
                }
            )
        }

        // 🔹 Tournament Detail
        composable(
            route = "${Routes.TOURNAMENT_DETAIL}/{tournamentId}",
            arguments = listOf(
                navArgument("tournamentId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            TournamentDetailScreen(
                tournamentId = backStackEntry.arguments?.getString("tournamentId") ?: ""
            )
        }

        // 🔹 Withdraw
        composable(Routes.WITHDRAW) {
            WithdrawScreen()
        }
    }
}
