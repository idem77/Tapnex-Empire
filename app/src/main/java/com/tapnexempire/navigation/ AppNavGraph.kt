package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tapnexempire.screens.*

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {

        composable("splash") { SplashScreen(onContinue = { navController.navigate("home") }) }

        composable("home") { HomeScreen(
            onWalletClick = { navController.navigate("wallet") },
            onTaskClick = { navController.navigate("task") },
            onGameClick = { navController.navigate("game") },
            onRedeemClick = { navController.navigate("redeem") },
            onTournamentClick = { navController.navigate("tournament") },
            onProfileClick = { navController.navigate("profile") }
        )}

        composable("wallet") { WalletScreen() }
        composable("task") { TaskScreen() }
        composable("game") { GameScreen() }
        composable("redeem") { RedeemScreen() }
        composable("tournament") { TournamentScreen() }
        composable("profile") { ProfileScreen() }
    }
}
