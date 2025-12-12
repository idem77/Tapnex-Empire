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
import com.tapnexempire.ui.tournament.TournamentListScreen
import com.tapnexempire.ui.tournament.MyTournamentScreen
import com.tapnexempire.ui.tournament.TournamentDetailScreen
import com.tapnexempire.ui.wallet.WalletScreen
import com.tapnexempire.viewmodel.AuthViewModel
import com.tapnexempire.viewmodel.WalletViewModel

@Composable
fun AppNavGraph(navController: NavHostController) {

    val authViewModel: AuthViewModel = hiltViewModel()
    val walletViewModel: WalletViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = "otpLogin"
    ) {

        // OTP Login Screen
        composable("otpLogin") {
            OtpLoginScreen(
                onOtpSent = {
                    navController.navigate("otpVerification")
                }
            )
        }

        // OTP Verification Screen
        composable("otpVerification") {
            OtpVerificationScreen(
                phoneNumber = "", // pass actual number if needed
                onVerified = {
                    navController.navigate("home") {
                        popUpTo("otpLogin") { inclusive = true }
                    }
                }
            )
        }

        // Home Screen
        composable("home") {
            HomeScreen(
                coins = walletViewModel.walletState.value.totalCoins,
                onWalletClick = { navController.navigate("wallet") },
                onTournamentClick = { navController.navigate("tournamentList") },
                onProfileClick = { navController.navigate("profile") }
            )
        }

        // Wallet Screen
        composable("wallet") {
            WalletScreen(
                totalCoins = walletViewModel.walletState.value.totalCoins,
                transactions = walletViewModel.walletState.value.transactions,
                onDepositClick = { /* handle deposit */ },
                onDailyBonusClick = { /* handle daily bonus */ }
            )
        }

        // Tournament List Screen
        composable("tournamentList") {
            TournamentListScreen(
                tournaments = listOf(
                    // sample data, replace with real
                    com.tapnexempire.ui.tournament.Tournament(1, "Daily Battle", "500 coins"),
                    com.tapnexempire.ui.tournament.Tournament(2, "Weekly Clash", "2000 coins")
                ),
                onTournamentClick = { tournament ->
                    navController.navigate("tournamentDetail/${tournament.id}")
                }
            )
        }

        // Tournament Detail Screen (example navigation with arg)
        composable("tournamentDetail/{tournamentId}") { backStackEntry ->
            val tournamentId = backStackEntry.arguments?.getString("tournamentId") ?: "0"
            TournamentDetailScreen(
                detail = com.tapnexempire.ui.tournament.TournamentDetail(
                    name = "Tournament $tournamentId",
                    prize = "1000 coins",
                    entryFee = 100,
                    participants = 10
                ),
                onJoinClick = { /* handle join */ }
            )
        }

        // My Tournament Screen
        composable("myTournaments") {
            MyTournamentScreen(
                myTournaments = listOf(
                    com.tapnexempire.ui.tournament.MyTournament(1, "Daily Battle", "500 coins")
                ),
                onTournamentClick = { /* handle click */ }
            )
        }

        // Profile Screen
        composable("profile") {
            ProfileScreen(
                userName = "Tapnex Player",
                onEditProfileClick = { /* edit profile */ },
                onSettingsClick = { /* open settings */ },
                onLogout = {
                    authViewModel.logout()
                    navController.navigate("otpLogin") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
    }
}
