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
import com.tapnexempire.models.Game
import com.tapnexempire.models.Task
import com.tapnexempire.models.Transaction

object Screen {
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
    NavHost(navController = navController, startDestination = Screen.Login) {

        composable(Screen.Login) {
            LoginScreen(
                onLoginClick = { navController.navigate(Screen.OtpVerification) },
                onSignupClick = { navController.navigate(Screen.Signup) }
            )
        }

        composable(Screen.Signup) {
            SignupScreen(
                onSignupClick = { navController.navigate(Screen.OtpVerification) },
                onLoginClick = { navController.popBackStack() }
            )
        }

        composable(Screen.OtpVerification) {
            OtpVerificationScreen(
                phoneNumber = "",
                onVerifyClick = { navController.navigate(Screen.Home) }
            )
        }

        composable(Screen.Home) {
            HomeScreen(
                coins = 500,
                gameList = listOf(
                    Game("Ludo", "Classic Ludo game", 0),
                    Game("Carrom", "Carrom board fun", 0),
                    Game("Chess", "Mind strategy game", 0)
                ),
                onGameClick = {}
            )
        }

        composable(Screen.Wallet) {
            WalletScreen(
                depositBalance = 2000,
                withdrawableBalance = 1500,
                referralRewards = listOf(
                    "Invite Friend +100",
                    "Daily Bonus +50"
                ),
                onDepositClick = { navController.navigate(Screen.Deposit) },
                onWithdrawClick = { navController.navigate(Screen.Withdraw) },
                onTransactionHistoryClick = { navController.navigate(Screen.TransactionHistory) }
            )
        }

        composable(Screen.Deposit) {
            DepositScreen(onDepositClick = {}, currentDepositBalance = 2000)
        }

        composable(Screen.Withdraw) {
            WithdrawScreen(onWithdrawClick = {}, currentWithdrawableBalance = 1500)
        }

        composable(Screen.TransactionHistory) {
            TransactionHistoryScreen(
                transactions = listOf(
                    Transaction("Deposit", "+500", "26 Sept 2025"),
                    Transaction("Withdraw", "-200", "25 Sept 2025")
                )
            )
        }

        composable(Screen.Profile) {
            ProfileScreen(
                userName = "Queen 👑",
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
            EditProfileScreen(currentName = "Queen 👑", onSaveClick = {})
        }

        composable(Screen.TournamentList) {
            TournamentListScreen(
                tournaments = listOf(
                    Tournament("Ludo Tournament", 50, 1000),
                    Tournament("Carrom Tournament", 100, 2000)
                ),
                onTournamentClick = { navController.navigate(Screen.TournamentDetail) }
            )
        }

        composable(Screen.TournamentDetail) {
            TournamentDetailScreen(
                tournament = Tournament("Ludo Tournament", 50, 1000),
                onJoinClick = {}
            )
        }

        composable(Screen.MyTournaments) {
            MyTournamentsScreen(
                myTournaments = listOf(
                    Tournament("Chess Tournament", 30, 500)
                )
            )
        }

        composable(Screen.Task) {
            TaskScreen(
                tasks = listOf(
                    Task("Complete Profile", false),
                    Task("Play 1 Game", true),
                    Task("Invite a Friend", false)
                ),
                onTaskComplete = {}
            )
        }
    }
}
