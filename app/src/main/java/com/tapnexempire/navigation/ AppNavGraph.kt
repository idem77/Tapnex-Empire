package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
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
import com.tapnexempire.ui.tournament.Tournament
import com.tapnexempire.ui.splash.SplashScreen

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

        // Splash
        composable(Screen.Splash) {
            SplashScreen(
                onTimeout = { navController.navigate(Screen.Login) }
            )
        }

        // Login
        composable(Screen.Login) {
            LoginScreen(
                onLoginClick = { navController.navigate(Screen.OtpVerification) },
                onSignupClick = { navController.navigate(Screen.Signup) }
            )
        }

        // Signup
        composable(Screen.Signup) {
            SignupScreen(
                onSignupClick = { navController.navigate(Screen.OtpVerification) },
                onLoginClick = { navController.popBackStack() }
            )
        }

        // OTP Verification
        composable(Screen.OtpVerification) {
            OtpVerificationScreen(
                phoneNumber = "",
                onVerifyClick = { navController.navigate(Screen.Home) }
            )
        }

        // Home (dummy data so UI shows)
        composable(Screen.Home) {
            HomeScreen(
                coins = 120,
                gameList = listOf("Ludo", "Carrom", "Chess"),
                onGameClick = { /* TODO */ }
            )
        }

        // Wallet
        composable(Screen.Wallet) {
            WalletScreen(
                depositBalance = 500,
                withdrawableBalance = 200,
                referralRewards = listOf("Bonus 50 Coins", "Task Reward 20"),
                onDepositClick = { navController.navigate(Screen.Deposit) },
                onWithdrawClick = { navController.navigate(Screen.Withdraw) },
                onTransactionHistoryClick = { navController.navigate(Screen.TransactionHistory) }
            )
        }

        // Deposit
        composable(Screen.Deposit) {
            DepositScreen(onDepositClick = {}, currentDepositBalance = 500)
        }

        // Withdraw
        composable(Screen.Withdraw) {
            WithdrawScreen(onWithdrawClick = {}, currentWithdrawableBalance = 200)
        }

        // Transaction History
        composable(Screen.TransactionHistory) {
            TransactionHistoryScreen(
                transactions = listOf("Deposit: +500", "Withdraw: -200", "Reward: +50")
            )
        }

        // Profile
        composable(Screen.Profile) {
            ProfileScreen(
                userName = "Queen 👑",
                onEditProfileClick = { navController.navigate(Screen.EditProfile) },
                onSettingsClick = { navController.navigate(Screen.Settings) }
            )
        }

        // Settings
        composable(Screen.Settings) {
            SettingsScreen(
                notificationsEnabled = true,
                onNotificationToggle = {},
                onHelpClick = {}
            )
        }

        // Edit Profile
        composable(Screen.EditProfile) {
            EditProfileScreen(currentName = "Queen 👑", onSaveClick = {})
        }

        // Tournament List
        composable(Screen.TournamentList) {
            TournamentListScreen(
                tournaments = listOf(
                    Tournament("Ludo Clash", 50, 1000),
                    Tournament("Carrom King", 20, 500)
                ),
                onTournamentClick = { navController.navigate(Screen.TournamentDetail) }
            )
        }

        // Tournament Detail
        composable(Screen.TournamentDetail) {
            TournamentDetailScreen(
                tournament = Tournament("Ludo Clash", 50, 1000),
                onJoinClick = {}
            )
        }

        // My Tournaments
        composable(Screen.MyTournaments) {
            MyTournamentsScreen(
                myTournaments = listOf(
                    Tournament("Carrom King", 20, 500)
                )
            )
        }

        // Task
        composable(Screen.Task) {
            TaskScreen(
                tasks = listOf("Watch Ad", "Refer Friend", "Daily Login"),
                onTaskComplete = {}
            )
        }
    }
}
