package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.ui.splash.SplashScreen
import com.tapnexempire.ui.auth.OtpLoginScreen
import com.tapnexempire.ui.auth.OtpVerificationScreen
import com.tapnexempire.ui.home.HomeScreen
import com.tapnexempire.ui.wallet.WalletScreen
import com.tapnexempire.ui.tournament.TournamentScreen
import com.tapnexempire.ui.profile.ProfileScreen
import com.tapnexempire.viewmodel.AuthViewModel
import com.tapnexempire.viewmodel.WalletViewModel
import com.tapnexempire.viewmodel.TournamentViewModel

@Composable
fun AppNavGraph(navController: NavHostController) {

    val authViewModel: AuthViewModel = hiltViewModel()
    val walletViewModel: WalletViewModel = hiltViewModel()
    val tournamentViewModel: TournamentViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {

        // Splash Screen
        composable("splash") {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate("login") {
                        popUpTo("splash") { inclusive = true }
                    }
                },
                onNavigateToHome = {
                    navController.navigate("home") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }

        // OTP Login Screen
        composable("login") {
            OtpLoginScreen(
                authViewModel = authViewModel,
                onOtpSent = {
                    navController.navigate("otpVerification")
                }
            )
        }

        // OTP Verification Screen
        composable("otpVerification") {
            OtpVerificationScreen(
                authViewModel = authViewModel,
                onVerificationSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        // Home Screen
        composable("home") {
            HomeScreen(
                onWalletClick = { navController.navigate("wallet") },
                onTournamentClick = { navController.navigate("tournament") },
                onProfileClick = { navController.navigate("profile") }
            )
        }

        // Wallet Screen
        composable("wallet") {
            WalletScreen(
                walletViewModel = walletViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        // Tournament Screen
        composable("tournament") {
            TournamentScreen(
                tournamentViewModel = tournamentViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        // Profile Screen
        composable("profile") {
            ProfileScreen(
                userName = "Tapnex User",
                onEditProfileClick = {},
                onSettingsClick = {},
            )
        }
    }
}
