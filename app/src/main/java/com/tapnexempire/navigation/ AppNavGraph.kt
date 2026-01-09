package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
import com.tapnexempire.ui.wallet.DepositScreen
import com.tapnexempire.ui.wallet.TransactionHistoryScreen
import com.tapnexempire.ui.wallet.WalletScreen
import com.tapnexempire.ui.wallet.WithdrawScreen
import com.tapnexempire.viewmodel.HomeViewModel
import com.tapnexempire.viewmodel.TaskViewModel
import com.tapnexempire.viewmodel.TournamentDetailViewModel
import com.tapnexempire.viewmodel.TournamentViewModel
import com.tapnexempire.viewmodel.WalletViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    walletViewModel: WalletViewModel,
    taskViewModel: TaskViewModel,
    tournamentViewModel: TournamentViewModel,
    tournamentDetailViewModel: TournamentDetailViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        // SPLASH
        composable("splash") {
            SplashScreen(
                navController = navController
            )
        }

        // AUTH
        composable("otp_login") {
            OtpLoginScreen(
                navController = navController,
                onOtpSent = { phoneNumber ->
                    navController.navigate("otp_verification/$phoneNumber")
                }
            )
        }

        composable("otp_verification/{phoneNumber}") { backStackEntry ->
            val phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: ""
            OtpVerificationScreen(
                navController = navController,
                phoneNumber = phoneNumber,
                onVerified = {
                    navController.navigate("home") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }

        // HOME
        composable("home") {
            HomeScreen(
                navController = navController,
                homeViewModel = homeViewModel,
                onWalletClick = { navController.navigate("wallet") },
                onTaskClick = { navController.navigate("task") },
                onTournamentClick = { tournamentId ->
                    navController.navigate("tournament_detail/$tournamentId")
                },
                onProfileClick = { navController.navigate("profile") }
            )
        }

        // WALLET
        composable("wallet") {
            WalletScreen(
                navController = navController,
                walletViewModel = walletViewModel,
                onDepositClick = { navController.navigate("deposit") },
                onWithdrawClick = { navController.navigate("withdraw") },
                onTransactionHistoryClick = { navController.navigate("transaction_history") }
            )
        }

        composable("deposit") {
            DepositScreen(
                navController = navController,
                walletViewModel = walletViewModel,
                onDepositCoins = { /* handle deposit */ }
            )
        }

        composable("withdraw") {
            WithdrawScreen(
                navController = navController,
                walletViewModel = walletViewModel,
                onWithdrawCoins = { /* handle withdraw */ }
            )
        }

        composable("transaction_history") {
            TransactionHistoryScreen(
                navController = navController,
                walletViewModel = walletViewModel
            )
        }

        // TASK
        composable("task") {
            TaskScreen(
                navController = navController,
                taskViewModel = taskViewModel,
                onCompleteTask = { taskId ->
                    taskViewModel.completeTask(taskId)
                }
            )
        }

        // TOURNAMENT
        composable("tournament_list") {
            TournamentListScreen(
                navController = navController,
                tournamentViewModel = tournamentViewModel,
                onTournamentClick = { tournamentId ->
                    navController.navigate("tournament_detail/$tournamentId")
                }
            )
        }

        composable("tournament_detail/{tournamentId}") { backStackEntry ->
            val tournamentId = backStackEntry.arguments?.getString("tournamentId") ?: ""
            TournamentDetailScreen(
                navController = navController,
                tournamentDetailViewModel = tournamentDetailViewModel,
                tournamentId = tournamentId,
                onJoinTournament = { /* handle join */ }
            )
        }

        composable("my_tournaments") {
            MyTournamentsScreen(
                navController = navController,
                tournamentViewModel = tournamentViewModel
            )
        }

        // PROFILE
        composable("profile") {
            ProfileScreen(
                navController = navController,
                onEditProfileClick = { navController.navigate("edit_profile") },
                onSettingsClick = { navController.navigate("settings") }
            )
        }

        composable("edit_profile") {
            EditProfileScreen(
                navController = navController,
                onSaveProfile = { navController.popBackStack() }
            )
        }

        composable("settings") {
            SettingsScreen(
                navController = navController,
                onLogout = { navController.navigate("otp_login") }
            )
        }
    }
}
