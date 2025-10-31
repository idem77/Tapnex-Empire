package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tapnexempire.ui.splash.SplashScreen
import com.tapnexempire.ui.auth.LoginScreen
import com.tapnexempire.ui.auth.SignupScreen
import com.tapnexempire.ui.auth.OtpVerificationScreen
import com.tapnexempire.ui.home.HomeScreen
import com.tapnexempire.ui.wallet.WalletScreen
import com.tapnexempire.ui.wallet.DepositScreen
import com.tapnexempire.ui.wallet.WithdrawScreen
import com.tapnexempire.ui.wallet.TransactionHistoryScreen
import com.tapnexempire.ui.task.TaskScreen
import com.tapnexempire.ui.tournament.TournamentListScreen
import com.tapnexempire.ui.tournament.TournamentDetailScreen
import com.tapnexempire.ui.tournament.MyTournamentsScreen
import com.tapnexempire.ui.profile.ProfileScreen
import com.tapnexempire.ui.profile.EditProfileScreen
import com.tapnexempire.ui.profile.SettingsScreen
import com.tapnexempire.viewmodel.AuthViewModel
import com.tapnexempire.viewmodel.WalletViewModel
import com.tapnexempire.viewmodel.TournamentViewModel

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
        // Splash
        composable("splash") {
            SplashScreen(navController)
        }

        // Auth flow
        composable("login") {
            LoginScreen(navController, authViewModel)
        }
        composable("signup") {
            SignupScreen(navController, authViewModel)
        }
        composable("otp") {
            OtpVerificationScreen(navController, authViewModel)
        }

        // Home
        composable("home") {
            HomeScreen(navController, authViewModel)
        }

        // Wallet
        composable("wallet") {
            WalletScreen(navController, walletViewModel)
        }
        composable("deposit") {
            DepositScreen(navController, walletViewModel)
        }
        composable("withdraw") {
            WithdrawScreen(navController, walletViewModel)
        }
        composable("transactions") {
            TransactionHistoryScreen(navController, walletViewModel)
        }

        // Tasks
        composable("tasks") {
            TaskScreen(navController)
        }

        // Tournaments
        composable("tournaments") {
            TournamentListScreen(navController, tournamentViewModel)
        }
        composable("tournament_detail") {
            TournamentDetailScreen(navController, tournamentViewModel)
        }
        composable("my_tournaments") {
            MyTournamentsScreen(navController, tournamentViewModel)
        }

        // Profile & Settings
        composable("profile") {
            ProfileScreen(navController, authViewModel)
        }
        composable("edit_profile") {
            EditProfileScreen(navController, authViewModel)
        }
        composable("settings") {
            SettingsScreen(navController, authViewModel)
        }
    }
}
