package com.tapnexempire.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tapnexempire.components.BottomNavBar
import com.tapnexempire.screen.*

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "splash",
            modifier = Modifier.padding(innerPadding)
        ) {
            // ✅ Splash
            composable("splash") {
                SplashScreen(
                    onTimeout = {
                        navController.navigate("auth") {
                            popUpTo("splash") { inclusive = true }
                        }
                    }
                )
            }

            // ✅ Auth
            composable("auth") {
                AuthScreen(
                    onContinue = {
                        navController.navigate("home") {
                            popUpTo("auth") { inclusive = true }
                        }
                    }
                )
            }

            // ✅ Home
            composable("home") {
                HomeScreen(
                    onWalletClick = { navController.navigate("wallet") },
                    onGameClick = { navController.navigate("game") }
                )
            }

            // ✅ Wallet
            composable("wallet") {
                WalletScreen(
                    onRedeemClick = { navController.navigate("redeem") },
                    onBackClick = { navController.popBackStack() }
                )
            }

            // ✅ Task
            composable("task") {
                TaskScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }

            // ✅ Game
            composable("game") {
                GameScreen(
                    onPlayClick = { /* TODO: add actual play logic */ },
                    onBackClick = { navController.popBackStack() }
                )
            }

            // ✅ Redeem
            composable("redeem") {
                RedeemScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }

            // ✅ Tournament
            composable("tournament") {
                TournamentScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }

            // ✅ Profile
            composable("profile") {
                ProfileScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}
