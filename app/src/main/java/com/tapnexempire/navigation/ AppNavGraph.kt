package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tapnexempire.ui.auth.OtpLoginScreen
import com.tapnexempire.ui.auth.OtpVerificationScreen
import com.tapnexempire.ui.home.HomeScreen
import com.tapnexempire.ui.profile.ProfileScreen
import com.tapnexempire.ui.settings.SettingsScreen
import com.tapnexempire.ui.splash.SplashScreen
import com.tapnexempire.ui.tasks.TaskScreen
import com.tapnexempire.ui.tournament.*
import com.tapnexempire.ui.wallet.*
import com.tapnexempire.viewmodel.AuthViewModel
import com.tapnexempire.viewmodel.WalletViewModel

@Composable
fun AppNavGraph(navController: NavHostController) {

    val authViewModel: AuthViewModel = hiltViewModel()
    val walletViewModel: WalletViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {

        // 🔹 Splash
        composable("splash") {
            SplashScreen(
                onNavigate = { isLoggedIn ->
                    if (isLoggedIn) {
                        navController.navigate("home") {
                            popUpTo("splash") { inclusive = true }
                        }
                    } else {
                        navController.navigate("otpLogin") {
                            popUpTo("splash") { inclusive = true }
                        }
                    }
                }
            )
        }

        // 🔐 Auth
        composable("otpLogin") {
            OtpLoginScreen(
                onOtpSent = {
                    navController.navigate("otpVerification")
                }
            )
        }

        composable("otpVerification") {
            OtpVerificationScreen(
                phoneNumber = "",
                onVerified = {
                    navController.navigate("home") {
                        popUpTo("otpLogin") { inclusive = true }
                    }
                }
            )
        }

        // 🏠 Home
        composable("home") {
            HomeScreen(
                coins = walletViewModel.walletState.value.totalCoins,
                onWalletClick = { navController.navigate("wallet") },
                onTournamentClick = { navController.navigate("tournamentList") },
                onProfileClick = { navController.navigate("profile") }
            )
        }

        // 💰 Wallet
        composable("wallet") {
            WalletScreen(
                totalCoins = walletViewModel.walletState.value.totalCoins,
                transactions = walletViewModel.walletState.value.transactions,
                onDepositClick = { navController.navigate("deposit") },
                onWithdrawClick = { navController.navigate("withdraw") },
                onHistoryClick = { navController.navigate("transactions") }
            )
        }

        composable("deposit") { DepositScreen() }
        composable("withdraw") { WithdrawScreen() }
        composable("transactions") { TransactionHistoryScreen() }

        // 🏆 Tournament
        composable("tournamentList") {
            TournamentListScreen(
                tournaments = emptyList(),
                onTournamentClick = {
                    navController.navigate("tournamentDetail/${it.id}")
                }
            )
        }

        composable("tournamentDetail/{id}") {
            TournamentDetailScreen(
                detail = TournamentDetail(
                    name = "Tapnex Battle",
                    prize = "Winning Coins",
                    entryFee = 100,
                    participants = 100
                ),
                onJoinClick = {}
            )
        }

        composable("myTournaments") {
            MyTournamentScreen(
                myTournaments = emptyList(),
                onTournamentClick = {}
            )
        }

        // 📋 Tasks
        composable("tasks") {
            TaskScreen()
        }

        // 👤 Profile
        composable("profile") {
            ProfileScreen(
                userName = "Tapnex Player",
                onEditProfileClick = {},
                onSettingsClick = { navController.navigate("settings") },
                onLogout = {
                    authViewModel.logout()
                    navController.navigate("otpLogin") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }

        // ⚙️ Settings
        composable("settings") {
            SettingsScreen()
        }
    }
}
