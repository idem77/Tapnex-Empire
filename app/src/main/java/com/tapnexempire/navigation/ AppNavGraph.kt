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

        composable(Screen.Splash) {
            SplashScreen(
                onTimeout = {
                    navController.navigate(Screen.Login) {
                        popUpTo(Screen.Splash) { inclusive = true }
                    }
                }
            )
        }

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
                coins = 0,
                gameList = emptyList(),
                onGameClick = {}
            )
        }

        composable(Screen.Wallet) {
            WalletScreen(
                depositBalance = 0,
                withdrawableBalance = 0,
                referralRewards = emptyList(),
                onDepositClick = { navController.navigate(Screen.Deposit) },
                onWithdrawClick = { navController.navigate(Screen.Withdraw) },
                onTransactionHistoryClick = { navController.navigate(Screen.TransactionHistory) }
            )
        }

        composable(Screen.Deposit) {
            DepositScreen(onDepositClick = {}, currentDepositBalance = 0)
        }

        composable(Screen.Withdraw) {
            WithdrawScreen(onWithdrawClick = {}, currentWithdrawableBalance = 0)
        }

        composable(Screen.TransactionHistory) {
            TransactionHistoryScreen(transactions = emptyList())
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
            TournamentListScreen(tournaments = emptyList(), onTournamentClick = { navController.navigate(Screen.TournamentDetail) })
        }

        composable(Screen.TournamentDetail) {
            TournamentDetailScreen(tournament = Tournament("", 0, 0), onJoinClick = {})
        }

        composable(Screen.MyTournaments) {
            MyTournamentsScreen(myTournaments = emptyList())
        }

        composable(Screen.Task) {
            TaskScreen(tasks = emptyList(), onTaskComplete = {})
        }
    }
}
