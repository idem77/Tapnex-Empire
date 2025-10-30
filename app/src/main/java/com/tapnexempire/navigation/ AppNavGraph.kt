// TapnexEmpire/app/src/main/java/com/tapnexempire/navigation/AppNavGraph.kt
package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tapnexempire.models.TournamentModel
import com.tapnexempire.viewmodel.AuthViewModel
import com.tapnexempire.viewmodel.WalletViewModel
import com.tapnexempire.viewmodel.TournamentViewModel
import com.tapnexempire.ui.auth.*
import com.tapnexempire.ui.home.HomeScreen
import com.tapnexempire.ui.profile.*
import com.tapnexempire.ui.wallet.*
import com.tapnexempire.ui.task.TaskScreen
import com.tapnexempire.ui.splash.SplashScreen

// ✅ All routes for navigation
object Screen {
    const val Splash = "splash"
    const val Login = "login"
    const val Signup = "signup"
    const val OtpVerification = "otp_verification"
    const val Home = "home"
    const val Wallet = "wallet"
    const val Deposit = "deposit"
    const val Withdraw = "withdraw"
    const val TransactionHistory = "transaction_history"
    const val Profile = "profile"
    const val Settings = "settings"
    const val EditProfile = "edit_profile"
    const val TournamentList = "tournament_list"
    const val TournamentDetail = "tournament_detail/{tournamentId}"
    const val MyTournaments = "my_tournaments"
    const val Task = "task"
}

@Composable
fun AppNavGraph(navController: NavHostController) {

    // ✅ Inject all ViewModels with Hilt
    val authViewModel: AuthViewModel = hiltViewModel()
    val walletViewModel: WalletViewModel = hiltViewModel()
    val tournamentViewModel: TournamentViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash
    ) {
        // 🌀 Splash
        composable(Screen.Splash) {
            SplashScreen(
                onTimeout = {
                    navController.navigate(Screen.Login) {
                        popUpTo(Screen.Splash) { inclusive = true }
                    }
                }
            )
        }

        // 🔐 Auth Screens
        composable(Screen.Login) {
            LoginScreen(
                onLoginClick = { navController.navigate(Screen.OtpVerification) },
                onSignupClick = { navController.navigate(Screen.Signup) },
                viewModel = authViewModel
            )
        }

        composable(Screen.Signup) {
            SignupScreen(
                onSignupClick = { navController.navigate(Screen.OtpVerification) },
                onLoginClick = { navController.popBackStack() },
                viewModel = authViewModel
            )
        }

        composable(Screen.OtpVerification) {
            OtpVerificationScreen(
                phoneNumber = authViewModel.phoneNumber.value,
                onVerifyClick = { navController.navigate(Screen.Home) },
                viewModel = authViewModel
            )
        }

        // 🏠 Home
        composable(Screen.Home) {
            HomeScreen(
                coins = walletViewModel.totalCoins.value,
                gameList = tournamentViewModel.games.value,
                onGameClick = { gameId ->
                    navController.navigate("tournament_detail/$gameId")
                }
            )
        }

        // 💰 Wallet Screens
        composable(Screen.Wallet) {
            WalletScreen(
                viewModel = walletViewModel,
                onDepositClick = { navController.navigate(Screen.Deposit) },
                onWithdrawClick = { navController.navigate(Screen.Withdraw) },
                onTransactionHistoryClick = { navController.navigate(Screen.TransactionHistory) }
            )
        }

        composable(Screen.Deposit) {
            DepositScreen(
                onDepositClick = { amount -> walletViewModel.deposit(amount) },
                currentDepositBalance = walletViewModel.depositBalance.value
            )
        }

        composable(Screen.Withdraw) {
            WithdrawScreen(
                onWithdrawClick = { amount -> walletViewModel.withdraw(amount) },
                currentWithdrawableBalance = walletViewModel.withdrawableBalance.value
            )
        }

        composable(Screen.TransactionHistory) {
            TransactionHistoryScreen(transactions = walletViewModel.transactions.value)
        }

        // 👤 Profile Screens
        composable(Screen.Profile) {
            ProfileScreen(
                userName = authViewModel.userName.value,
                onEditProfileClick = { navController.navigate(Screen.EditProfile) },
                onSettingsClick = { navController.navigate(Screen.Settings) }
            )
        }

        composable(Screen.EditProfile) {
            EditProfileScreen(
                currentName = authViewModel.userName.value,
                onSaveClick = { newName ->
                    authViewModel.updateUserName(newName)
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Settings) {
            SettingsScreen(
                notificationsEnabled = true,
                onNotificationToggle = {},
                onHelpClick = {}
            )
        }

        // 🏆 Tournament Screens
        composable(Screen.TournamentList) {
            TournamentListScreen(
                tournaments = tournamentViewModel.tournaments.value,
                onTournamentClick = { id ->
                    navController.navigate("tournament_detail/$id")
                }
            )
        }

        composable("tournament_detail/{tournamentId}") { backStackEntry ->
            val tournamentId = backStackEntry.arguments?.getString("tournamentId") ?: ""
            val selectedTournament = tournamentViewModel.tournaments.value
                .find { it.id == tournamentId } ?: TournamentModel("", 0, 0)

            TournamentDetailScreen(
                tournament = selectedTournament,
                onJoinClick = { tournamentViewModel.joinTournament(selectedTournament) }
            )
        }

        composable(Screen.MyTournaments) {
            MyTournamentsScreen(myTournaments = tournamentViewModel.myTournaments.value)
        }

        // 🎯 Task Screen
        composable(Screen.Task) {
            TaskScreen(
                tasks = walletViewModel.dailyTasks.value,
                onTaskComplete = { taskId ->
                    walletViewModel.completeTask(taskId)
                }
            )
        }
    }
}
