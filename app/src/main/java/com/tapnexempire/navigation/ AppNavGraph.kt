package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.tapnexempire.ui.splash.SplashScreen
import com.tapnexempire.ui.auth.LoginScreen
import com.tapnexempire.ui.home.HomeScreen
import com.tapnexempire.ui.wallet.WalletScreen
import com.tapnexempire.ui.task.TaskScreen
import com.tapnexempire.ui.tournament.list.TournamentListScreen
import com.tapnexempire.ui.tournament.detail.TournamentDetailScreen
import com.tapnexempire.ui.withdraw.WithdrawScreen

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
    navController: NavHostController,
    startDestination: String = Routes.SPLASH
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(Routes.SPLASH) {
            SplashScreen(
                onNavigateNext = { isLoggedIn ->
                    navController.navigate(
                        if (isLoggedIn) Routes.HOME else Routes.LOGIN
                    ) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.LOGIN) {
            OtpLoginScreen(
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
                onTaskClick = { navController.navigate(Routes.TASKS) },
                onTournamentClick = { navController.navigate(Routes.TOURNAMENTS) }
            )
        }

        composable(Routes.WALLET) {
    WalletScreen(
        onDepositClick = { },
        onWithdrawClick = { },
        onTransactionClick = { }
    )
        }
        

        composable(Routes.TASKS) {
            TaskScreen()
        }

        composable(Routes.TOURNAMENTS) {
            TournamentListScreen(
                onTournamentClick = { tournamentId ->
                    navController.navigate(
                        "${Routes.TOURNAMENT_DETAIL}/$tournamentId"
                    )
                }
            )
        }

        composable(
            route = "${Routes.TOURNAMENT_DETAIL}/{tournamentId}",
            arguments = listOf(
                navArgument("tournamentId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val tournamentId =
                backStackEntry.arguments?.getString("tournamentId") ?: ""

            TournamentDetailScreen(
                tournamentId = tournamentId
            )
        }

        composable(Routes.WITHDRAW) {
            WithdrawScreen()
        }
    }
}
