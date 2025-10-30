package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavBackStackEntry
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
import com.tapnexempire.models.TournamentModel
import com.tapnexempire.models.Transaction
import com.tapnexempire.models.TaskModel
import com.tapnexempire.viewmodel.AuthViewModel
import com.tapnexempire.viewmodel.WalletViewModel
import com.tapnexempire.viewmodel.TournamentViewModel

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
    // tournament detail expects a tournamentId path parameter
    const val TournamentDetail = "tournament_detail/{tournamentId}"
    const val MyTournaments = "my_tournaments"
    const val Task = "task"
}

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    // Inject ViewModels via Hilt
    val authViewModel: AuthViewModel = hiltViewModel()
    val walletViewModel: WalletViewModel = hiltViewModel()
    val tournamentViewModel: TournamentViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Screen.Splash) {

        // Splash -> decides where to go (login/home)
        composable(Screen.Splash) {
            SplashScreen(
                onTimeout = {
                    navController.navigate(Screen.Login) {
                        popUpTo(Screen.Splash) { inclusive = true }
                    }
                }
            )
        }

        // Auth screens
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
            // phoneNumber guarded with safe access
            val phone = authViewModel.phoneNumber.value
            OtpVerificationScreen(
                phoneNumber = phone,
                onVerifyClick = { navController.navigate(Screen.Home) },
                viewModel = authViewModel
            )
        }

        // Home screen (shows coins, tournaments/games list)
        composable(Screen.Home) {
            HomeScreen(
                coins = walletViewModel.totalCoins.value,
                // tournamentViewModel may expose tournaments; use it as the list for home
                gameList = tournamentViewModel.tournaments.value,
                onGameClick = { gameId ->
                    navController.navigate("tournament_detail/$gameId")
                }
            )
        }

        // Wallet & related screens
        composable(Screen.Wallet) {
            WalletScreen(
                // pass current values; UI screens should accept these params or accept viewModel directly
                depositBalance = walletViewModel.depositBalance.value,
                withdrawableBalance = walletViewModel.withdrawableBalance.value,
                referralRewards = walletViewModel.referralRewards.value,
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
            // If your WalletViewModel.transactions is List<Transaction> adjust accordingly.
            // We pass the raw list; screens should accept List<Transaction> or List<String> as implemented.
            val txs = walletViewModel.transactions.value
            TransactionHistoryScreen(transactions = txs)
        }

        // Profile screens
        composable(Screen.Profile) {
            ProfileScreen(
                userName = authViewModel.userName.value,
                onEditProfileClick = { navController.navigate(Screen.EditProfile) },
                onSettingsClick = { navController.navigate(Screen.Settings) }
            )
        }

        composable(Screen.Settings) {
            SettingsScreen(
                notificationsEnabled = true,
                onNotificationToggle = {},
                onHelpClick = {}
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

        // Tournament list
        composable(Screen.TournamentList) {
            TournamentListScreen(
                tournaments = tournamentViewModel.tournaments.value,
                onTournamentClick = { tournamentId ->
                    navController.navigate("tournament_detail/$tournamentId")
                }
            )
        }

        // Tournament detail with argument
        composable(Screen.TournamentDetail) { backStackEntry: NavBackStackEntry ->
            val tournamentId = backStackEntry.arguments?.getString("tournamentId").orEmpty()
            val selectedTournament: TournamentModel =
                tournamentViewModel.tournaments.value.find { it.id == tournamentId }
                    ?: TournamentModel() // safe fallback

            TournamentDetailScreen(
                tournament = selectedTournament,
                onJoinClick = {
                    // join by id, or by passing tournament—match your ViewModel API
                    tournamentViewModel.joinTournament(selectedTournament.id, walletViewModel.totalCoins.value)
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        // My tournaments
        composable(Screen.MyTournaments) {
            MyTournamentsScreen(myTournaments = tournamentViewModel.myTournaments.value)
        }

        // Task / Daily tasks
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
