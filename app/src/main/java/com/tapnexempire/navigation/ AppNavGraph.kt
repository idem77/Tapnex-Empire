package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tapnexempire.viewmodel.AuthViewModel
import com.tapnexempire.viewmodel.WalletViewModel
import com.tapnexempire.viewmodel.TournamentViewModel
import com.tapnexempire.ui.auth.*
import com.tapnexempire.ui.home.HomeScreen
import com.tapnexempire.ui.splash.SplashScreen
import com.tapnexempire.ui.wallet.*
import com.tapnexempire.ui.tournament.*
import com.tapnexempire.ui.tasks.*
import com.tapnexempire.models.TaskModel
import com.tapnexempire.models.TransactionModel
import com.tapnexempire.models.TournamentModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    walletViewModel: WalletViewModel,
    tournamentViewModel: TournamentViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {

        // 🌀 Splash Screen
        composable("splash") {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate("login") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }

        // 🔐 Authentication Screens
        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("home") },
                authViewModel = authViewModel
            )
        }

        composable("signup") {
            SignupScreen(
                onSignupSuccess = { navController.navigate("home") },
                authViewModel = authViewModel
            )
        }

        // 🏠 Home
        composable("home") {
            HomeScreen(
                onWalletClick = { navController.navigate("wallet") },
                onTournamentClick = { navController.navigate("tournaments") },
                onTaskClick = { navController.navigate("tasks") },
                onProfileClick = { navController.navigate("profile") }
            )
        }

        // 💰 Wallet Screens
        composable("wallet") {
            WalletScreen(
                walletViewModel = walletViewModel,
                onDepositClick = { navController.navigate("deposit") },
                onWithdrawClick = { navController.navigate("withdraw") },
                onTransactionHistoryClick = { navController.navigate("transactions") }
            )
        }

        composable("deposit") {
            DepositScreen(
                walletViewModel = walletViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("withdraw") {
            WithdrawScreen(
                walletViewModel = walletViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("transactions") {
            TransactionHistoryScreen(
                transactions = walletViewModel.transactions.value
            )
        }

        // 🎯 Tasks
        composable("tasks") {
            TaskScreen(
                tasks = walletViewModel.tasks.value,
                onTaskComplete = { task: TaskModel ->
                    walletViewModel.completeTask(task)
                }
            )
        }

        // 🏆 Tournaments
        composable("tournaments") {
            TournamentListScreen(
                tournaments = tournamentViewModel.tournaments.value,
                onTournamentClick = { tournamentId ->
                    navController.navigate("tournament_detail/$tournamentId")
                }
            )
        }

        composable("tournament_detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            TournamentDetailScreen(
                tournament = tournamentViewModel.getTournamentById(id),
                onJoinClick = {
                    tournamentViewModel.joinTournament(id)
                    navController.navigate("my_tournaments")
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("my_tournaments") {
            MyTournamentsScreen(
                myTournaments = tournamentViewModel.myTournaments.value
            )
        }

        // 👤 Profile
        composable("profile") {
            ProfileScreen(
                userName = authViewModel.userName.value,
                onUpdateUserName = { newName ->
                    authViewModel.updateUserName(newName)
                },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
