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
import com.tapnexempire.ui.splash.SplashScreen
import com.tapnexempire.model.*
import com.tapnexempire.service.*

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
    NavHost(navController = navController, startDestination = Screen.Splash) {

        // --- Splash Screen ---
        composable(Screen.Splash) {
            SplashScreen(
                onTimeout = {
                    navController.navigate(Screen.Login) {
                        popUpTo(Screen.Splash) { inclusive = true }
                    }
                }
            )
        }

        // --- Auth Screens ---
        composable(Screen.Login) {
            LoginScreen(
                onLoginClick = { phone ->
                    AuthService.login(phone, "1234")
                    navController.navigate(Screen.OtpVerification)
                },
                onSignupClick = { navController.navigate(Screen.Signup) }
            )
        }

        composable(Screen.Signup) {
            SignupScreen(
                onSignupClick = { phone ->
                    AuthService.signup(phone, "1234")
                    navController.navigate(Screen.OtpVerification)
                },
                onLoginClick = { navController.popBackStack() }
            )
        }

        composable(Screen.OtpVerification) {
            OtpVerificationScreen(
                phoneNumber = "",
                onVerifyClick = { otp ->
                    if (AuthService.verifyOtp("", otp)) {
                        navController.navigate(Screen.Home)
                    }
                }
            )
        }

        // --- Home Screen ---
        composable(Screen.Home) {
            HomeScreen(
                coins = WalletService.getCoinBalance(),
                gameList = TournamentService.getGames(),
                onGameClick = {}
            )
        }

        // --- Wallet Screens ---
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
                onDepositClick = { amount -> WalletService.deposit(amount) },
                currentDepositBalance = WalletService.getDepositBalance()
            )
        }

        composable(Screen.Withdraw) {
            WithdrawScreen(
                onWithdrawClick = { amount -> WalletService.withdraw(amount) },
                currentWithdrawableBalance = WalletService.getWithdrawableBalance()
            )
        }

        composable(Screen.TransactionHistory) {
            TransactionHistoryScreen(transactions = WalletService.getTransactions())
        }

        // --- Profile Screens ---
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
                onSaveClick = { newName -> AuthService.updateProfile(newName) }
            )
        }

        // --- Tournament Screens ---
        composable(Screen.TournamentList) {
            TournamentListScreen(
                tournaments = TournamentService.getTournaments(),
                onTournamentClick = { navController.navigate(Screen.TournamentDetail) }
            )
        }

        composable(Screen.TournamentDetail) {
            TournamentDetailScreen(
                tournament = TournamentService.getTournamentDetail("1"),
                onJoinClick = { TournamentService.joinTournament("1") }
            )
        }

        composable(Screen.MyTournaments) {
            MyTournamentsScreen(myTournaments = TournamentService.getMyTournaments())
        }

        // --- Task Screen ---
        composable(Screen.Task) {
            TaskScreen(
                tasks = TaskService.getTasks(),
                onTaskComplete = { taskId -> TaskService.completeTask(taskId) }
            )
        }
    }
}
