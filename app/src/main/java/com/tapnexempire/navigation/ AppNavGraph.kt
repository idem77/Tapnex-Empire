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
import com.tapnexempire.ui.splash.SplashScreen
import com.tapnexempire.ui.tournament.TournamentListScreen
import com.tapnexempire.ui.wallet.WalletScreen
import com.tapnexempire.viewmodel.AuthViewModel
import com.tapnexempire.viewmodel.TournamentViewModel
import com.tapnexempire.viewmodel.WalletViewModel

@Composable
fun AppNavGraph(navController: NavHostController) {

    val authViewModel: AuthViewModel = hiltViewModel()
    val walletViewModel: WalletViewModel = hiltViewModel()
    val tournamentViewModel: TournamentViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        // 🚀 Splash Screen
        composable("splash") {
            SplashScreen(
                onTimeout = {
                    val isUserLoggedIn = authViewModel.isUserLoggedIn.value
                    if (isUserLoggedIn) {
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

        // 📱 OTP Login Screen
        composable("otpLogin") {
            OtpLoginScreen(
                viewModel = authViewModel,
                onOtpSent = {
                    navController.navigate("otpVerification")
                }
            )
        }

        // 🔐 OTP Verification Screen
        composable("otpVerification") {
            OtpVerificationScreen(
                viewModel = authViewModel,
                onSuccess = {
                    navController.navigate("home") {
                        popUpTo("otpLogin") { inclusive = true }
                    }
                }
            )
        }

        // 🏠 Home Screen
        composable("home") {
            HomeScreen(
                onWalletClick = { navController.navigate("wallet") },
                onTournamentClick = { navController.navigate("tournamentList") },
                onProfileClick = { navController.navigate("profile") }
            )
        }

        // 💰 Wallet Screen
        composable("wallet") {
            WalletScreen(
                walletViewModel = walletViewModel,
                onDepositClick = { /* later */ },
                onWithdrawClick = { /* later */ },
                onTransactionHistoryClick = { /* later */ }
            )
        }

        // 🏆 Tournament List
        composable("tournamentList") {
            TournamentListScreen(
                tournamentViewModel = tournamentViewModel,
                onTournamentClick = { /* later */ }
            )
        }

        // 👤 Profile Screen
        composable("profile") {
            ProfileScreen(
                userName = "Lazy King 👑",
                onEditProfileClick = { /* edit later */ },
                onSettingsClick = { /* settings later */ },
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
