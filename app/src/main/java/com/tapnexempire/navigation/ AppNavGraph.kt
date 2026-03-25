package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tapnexempire.ui.home.HomeScreen
import com.tapnexempire.ui.task.TaskScreen
import com.tapnexempire.ui.tournament.TournamentListScreen
import com.tapnexempire.ui.tournament.detail.TournamentDetailScreen
import com.tapnexempire.ui.wallet.WalletScreen
import com.tapnexempire.ui.wallet.WithdrawScreen
import com.tapnexempire.viewmodel.TournamentViewModel
import com.tapnexempire.viewmodel.WalletViewModel

object Routes {
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
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME,
        modifier = modifier
    ) {

        // 🔹 Home
        composable(Routes.HOME) {
            HomeScreen()
        }

        // 🔹 Wallet
        composable(Routes.WALLET) {
            val walletViewModel: WalletViewModel = hiltViewModel()
            WalletScreen(
                viewModel = walletViewModel,
                userId = "CURRENT_USER_ID",
                onTransactionClick = { /* future */ }
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
                userId = "CURRENT_USER_ID"
            )
        }

        // 🔹 Tournament Detail
        composable(
            route = "${Routes.TOURNAMENT_DETAIL}/{tournamentId}",
            arguments = listOf(navArgument("tournamentId") { type = NavType.StringType })
        ) { backStackEntry ->
            val tournamentId = backStackEntry.arguments?.getString("tournamentId") ?: ""
            TournamentDetailScreen(tournamentId = tournamentId)
        }

        // 🔹 Withdraw
        composable(Routes.WITHDRAW) {
            WithdrawScreen()
        }
    }
}
