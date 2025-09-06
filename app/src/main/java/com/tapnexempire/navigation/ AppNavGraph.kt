package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tapnexempire.screen.*

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {

        // Splash screen → goes to Home
        composable("splash") {
            SplashScreen(onTimeout = {
                navController.navigate("home") {
                    popUpTo("splash") { inclusive = true }
                }
            })
        }

        // Home screen → navigation buttons
        composable("home") {
            HomeScreen(
                onWalletClick = { navController.navigate("wallet") },
                onGameClick = { navController.navigate("game") }
            )
        }

        // Other screens (simple)
        composable("wallet") { WalletScreen() }
        composable("task") { TaskScreen() }
        composable("game") { GameScreen() }
        composable("redeem") { RedeemScreen() }
        composable("tournament") { TournamentScreen() }
        composable("profile") { ProfileScreen() }
        composable("auth") { AuthScreen() }
    }
}
