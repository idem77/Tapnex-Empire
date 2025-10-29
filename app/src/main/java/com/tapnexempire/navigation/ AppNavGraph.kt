package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tapnexempire.ui.auth.LoginScreen
import com.tapnexempire.ui.auth.OtpVerificationScreen
import com.tapnexempire.ui.auth.SignupScreen
import com.tapnexempire.ui.home.HomeScreen
import com.tapnexempire.ui.profile.ProfileScreen
import com.tapnexempire.ui.profile.SettingsScreen
import com.tapnexempire.ui.profile.EditProfileScreen
import com.tapnexempire.ui.wallet.WalletScreen
import com.tapnexempire.ui.wallet.DepositScreen
import com.tapnexempire.ui.wallet.WithdrawScreen
import com.tapnexempire.ui.wallet.TransactionHistoryScreen
import com.tapnexempire.ui.tournament.TournamentListScreen
import com.tapnexempire.ui.tournament.TournamentDetailScreen
import com.tapnexempire.ui.tournament.MyTournamentsScreen
import com.tapnexempire.ui.task.TaskScreen
import com.tapnexempire.ui.splash.SplashScreen

// ✅ Models
import com.tapnexempire.models.Game
import com.tapnexempire.models.User
import com.tapnexempire.models.TaskModel
import com.tapnexempire.models.TransactionModel
import com.tapnexempire.models.RewardType
import com.tapnexempire.models.TournamentModel
import com.tapnexempire.models.Wallet

// ✅ ViewModels
import com.tapnexempire.viewmodel.AuthViewModel
import com.tapnexempire.viewmodel.WalletViewModel
import com.tapnexempire.viewmodel.TournamentViewModel

// ✅ Routes
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

    // ✅ Inject ViewModels using Hilt
    val authViewModel: AuthViewModel = hiltViewModel()
    val walletViewModel: WalletViewModel = hiltViewModel()
    val tournamentViewModel: TournamentViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Screen.Splash) {

        // 🟢 Splash
        composable(Screen.Splash) {
            SplashScreen(
                onTimeout = {
                    navController.navigate(Screen.Login) {
                        popUpTo(Screen.Splash) { inclusive = true }
                    }
                }
            )
        }

        // 🟢 Login
        composable(Screen.Login) {
            LoginScreen(
                onLoginClick = { navController.navigate(Screen.OtpVerification) },
                onSignupClick = { navController.navigate(Screen.Signup) },
                viewModel = authViewModel
            )
        }

        // 🟢 Signup
        composable(Screen.Signup) {
            SignupScreen(
                onSignupClick = { navController.navigate(Screen.OtpVerification) },
                onLoginClick = { navController.popBackStack() },
                viewModel = authViewModel
            )
        }

        // 🟢 OTP
        composable(Screen.OtpVerification) {
            OtpVerificationScreen(
                phoneNumber = authViewModel.phoneNumber.value,
                onVerifyClick = { navController.navigate(Screen.Home) },
                viewModel = authViewModel
            )
        }

        // 🟢 Home
        composable(Screen.Home) {
            HomeScreen(
                coins = walletViewModel.totalCoins.value,
                gameList = tournamentViewModel.games.value,
                onGameClick = { gameId ->
                    navController.navigate("tournament_detail/$gameId")
                }
            )
        }

        // 🟢 Wallet
        composable(Screen.Wallet) {
            WalletScreen(
                depositBalance = walletViewModel.depositBalance.value,
                withdrawableBalance = walletViewModel.withdrawableBalance.value,
                referralRewards = walletViewModel.referralRewards.value,
                onDepositClick = { navController.navigate(Screen.Deposit) },
                onWithdrawClick = { navController.navigate(Screen.Withdraw) },
                onTransactionHistoryClick = { navController.navigate(Screen.TransactionHistory) }
            )
        }

        // 🟢 Deposit
        composable(Screen.Deposit) {
            DepositScreen(
                onDepositClick = { amount ->
                    walletViewModel.deposit(amount)
                },
                currentDepositBalance = walletViewModel.depositBalance.value
            )
        }

        // 🟢 Withdraw
        composable(Screen.Withdraw) {
            WithdrawScreen(
                onWithdrawClick = { amount ->
                    walletViewModel.withdraw(amount)
                },
                currentWithdrawableBalance = walletViewModel.withdrawableBalance.value
            )
        }

        // 🟢 Transaction History
        composable(Screen.TransactionHistory) {
            TransactionHistoryScreen(transactions = walletViewModel.transactions.value)
        }

        // 🟢 Profile
        composable(Screen.Profile) {
            ProfileScreen(
                userName = authViewModel.userName.value,
                onEditProfileClick = { navController.navigate(Screen.EditProfile) },
                onSettingsClick = { navController.navigate(Screen.Settings) }
            )
        }

        // 🟢 Settings
        composable(Screen.Settings) {
            SettingsScreen(
                notificationsEnabled = true,
                onNotificationToggle = {},
                onHelpClick = {}
            )
        }

        // 🟢 Edit Profile
        composable(Screen.EditProfile) {
            EditProfileScreen(
                currentName = authViewModel.userName.value,
                onSaveClick = { newName ->
                    authViewModel.updateUserName(newName)
                    navController.popBackStack()
                }
            )
        }

        // 🟢 Tournament List
        composable(Screen.TournamentList) {
            TournamentListScreen(
                tournaments = tournamentViewModel.tournaments.value,
                onTournamentClick = { tournamentId ->
                    navController.navigate("tournament_detail/$tournamentId")
                }
            )
        }

        // 🟢 Tournament Detail
        composable("tournament_detail/{tournamentId}") { backStackEntry ->
            val tournamentId = backStackEntry.arguments?.getString("tournamentId") ?: ""
            val selectedTournament = tournamentViewModel.tournaments.value
                .find { it.id == tournamentId } ?: TournamentModel("", 0, 0)

            TournamentDetailScreen(
                tournament = selectedTournament,
                onJoinClick = {
                    tournamentViewModel.joinTournament(selectedTournament)
                }
            )
        }

        // 🟢 My Tournaments
        composable(Screen.MyTournaments) {
            MyTournamentsScreen(myTournaments = tournamentViewModel.myTournaments.value)
        }

        // 🟢 Tasks
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
