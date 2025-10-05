package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tapnexempire.ui.auth.LoginScreen
import com.tapnexempire.ui.auth.OtpVerificationScreen
import com.tapnexempire.ui.auth.SignupScreen
import com.tapnexempire.ui.home.HomeScreen
import com.tapnexempire.ui.profile.EditProfileScreen
import com.tapnexempire.ui.profile.ProfileScreen
import com.tapnexempire.ui.profile.SettingsScreen
import com.tapnexempire.ui.task.TaskScreen
import com.tapnexempire.ui.tournament.MyTournamentsScreen
import com.tapnexempire.ui.tournament.TournamentDetailScreen
import com.tapnexempire.ui.tournament.TournamentListScreen
import com.tapnexempire.ui.wallet.DepositScreen
import com.tapnexempire.ui.wallet.TransactionHistoryScreen
import com.tapnexempire.ui.wallet.WalletScreen
import com.tapnexempire.ui.wallet.WithdrawScreen
import com.tapnexempire.model.Game
import com.tapnexempire.model.Transaction
import com.tapnexempire.model.Task
import com.tapnexempire.model.Tournament
import com.tapnexempire.service.AuthService
import com.tapnexempire.service.WalletService
import com.tapnexempire.service.TaskService
import com.tapnexempire.service.TournamentService

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
    const val TournamentDetail = "tournament_detail"
    const val MyTournaments = "my_tournaments"
    const val Task = "task"
}

@Composable
fun AppNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Login
    ) {

        // -------------------- AUTH SCREENS --------------------
        composable(Screen.Login) {
            LoginScreen(
                onLoginClick = { phone ->
                    AuthService.login(phone)
                    navController.navigate(Screen.OtpVerification)
                },
                onSignupClick = { navController.navigate(Screen.Signup) }
            )
        }

        composable(Screen.Signup) {
            SignupScreen(
                onSignupClick = { phone ->
                    AuthService.signup(phone)
                    navController.navigate(Screen.OtpVerification)
                },
                onLoginClick = { navController.popBackStack() }
            )
        }

        composable(Screen.OtpVerification) {
            OtpVerificationScreen(
                phoneNumber = AuthService.getCurrentPhone(),
                onVerifyClick = { otp ->
                    val success = AuthService.verifyOtp(AuthService.getCurrentPhone(), otp)
                    if (success) {
                        navController.navigate(Screen.Home) {
                            popUpTo(Screen.Login) { inclusive = true }
                        }
                    }
                }
            )
        }

        // -------------------- HOME SCREEN --------------------
        composable(Screen.Home) {
            val games: List<Game> = TournamentService.getGames()
            HomeScreen(
                coins = WalletService.getCoinBalance(),
                gameList = games,
                onGameClick = { gameId ->
                    // Navigate to tournament list for that game
                    navController.navigate(Screen.TournamentList)
                }
            )
        }

        // -------------------- WALLET SCREENS --------------------
        composable(Screen.Wallet) {
            WalletScreen(
                depositBalance = WalletService.getDepositBalance(),
                withdrawableBalance = WalletService.getWithdrawableBalance(),
                referralRewards = WalletService.getReferralRewards(),
                onDepositClick = { navController.navigate(Screen.Deposit) },
                onWithdrawClick = { navController.navigate(Screen.Withdraw) },
                onTransactionHistoryClick = { navController.navigate(Screen.TransactionHistory) }
            )
        }

        composable(Screen.Deposit) {
            DepositScreen(
                currentDepositBalance = WalletService.getDepositBalance(),
                onDepositClick = { amount ->
                    WalletService.deposit(amount)
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Withdraw) {
            WithdrawScreen(
                currentWithdrawableBalance = WalletService.getWithdrawableBalance(),
                onWithdrawClick = { amount ->
                    WalletService.withdraw(amount)
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.TransactionHistory) {
            val transactions: List<Transaction> = WalletService.getTransactions()
            TransactionHistoryScreen(transactions = transactions)
        }

        // -------------------- PROFILE SCREENS --------------------
        composable(Screen.Profile) {
            ProfileScreen(
                userName = AuthService.getCurrentUserName(),
                onEditProfileClick = { navController.navigate(Screen.EditProfile) },
                onSettingsClick = { navController.navigate(Screen.Settings) }
            )
        }

        composable(Screen.Settings) {
            SettingsScreen(
                notificationsEnabled = AuthService.areNotificationsEnabled(),
                onNotificationToggle = { enabled -> AuthService.setNotifications(enabled) },
                onHelpClick = {}
            )
        }

        composable(Screen.EditProfile) {
            EditProfileScreen(
                currentName = AuthService.getCurrentUserName(),
                onSaveClick = { newName ->
                    AuthService.updateProfile(newName)
                    navController.popBackStack()
                }
            )
        }

        // -------------------- TOURNAMENT SCREENS --------------------
        composable(Screen.TournamentList) {
            val tournaments: List<Tournament> = TournamentService.getTournaments()
            TournamentListScreen(
                tournaments = tournaments,
                onTournamentClick = { id ->
                    navController.navigate(Screen.TournamentDetail)
                }
            )
        }

        composable(Screen.TournamentDetail) {
            val tournament = TournamentService.getTournamentDetail("1")
            TournamentDetailScreen(
                tournament = tournament,
                onJoinClick = { TournamentService.joinTournament("1") }
            )
        }

        composable(Screen.MyTournaments) {
            MyTournamentsScreen(myTournaments = TournamentService.getMyTournaments())
        }

        // -------------------- TASK SCREEN --------------------
        composable(Screen.Task) {
            val tasks: List<Task> = TaskService.getTasks()
            TaskScreen(
                tasks = tasks,
                onTaskComplete = { taskId -> TaskService.completeTask(taskId) }
            )
        }
    }
}
