package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tapnexempire.ui.auth.LoginScreen
import com.tapnexempire.ui.home.HomeScreen
import com.tapnexempire.ui.wallet.WalletScreen
import com.tapnexempire.ui.profile.ProfileScreen
import com.tapnexempire.ui.tournament.TournamentScreen
import com.tapnexempire.ui.splash.SplashScreen
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
            SplashScreen(
                onNavigateToLogin = { navController.navigate("login") },
                onNavigateToHome = { navController.navigate("home") }
            )
        }

        // Login
        composable("login") {
            LoginScreen(
                authViewModel = authViewModel,
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        // Home
        composable("home") {
            HomeScreen(
                coins = walletViewModel.coins,
                gameList = tournamentViewModel.tournamentList,
                onWalletClick = { navController.navigate("wallet") },
                onTournamentClick = { navController.navigate("tournament") },
                onProfileClick = { navController.navigate("profile") },
                onGameClick = { /* TODO: Add game detail */ }
            )
        }

        // Wallet
        composable("wallet") {
            WalletScreen(
                walletViewModel = walletViewModel,
                onDepositClick = { /* Deposit flow */ },
                onWithdrawClick = { /* Withdraw flow */ },
                onTransactionHistoryClick = { /* History flow */ }
            )
        }

        // Tournament
        composable("tournament") {
            TournamentScreen(
                tournamentViewModel = tournamentViewModel,
                onTournamentClick = { /* Join tournament */ }
            )
        }

        // Profile
        composable("profile") {
            ProfileScreen(
                userName = "Tapnex Player",
                onEditProfileClick = { /* TODO */ },
                onSettingsClick = { /* TODO */ }
            )
        }
    }
}
