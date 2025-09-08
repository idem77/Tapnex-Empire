package com.tapnexempire.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
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
                    onTimeout = { navController.navigate("auth") { popUpTo("splash") { inclusive = true } } }
                )
            }

            // ✅ Auth
            composable("auth") {
                AuthScreen(
                    onContinue = { navController.navigate("home") { popUpTo("auth") { inclusive = true } } }
                )
            }

            // ✅ Home (with params)
            composable("home") {
                HomeScreen(
                    onWalletClick = { navController.navigate("wallet") },
                    onGameClick = { navController.navigate("game") }
                )
            }

            // ✅ Other screens
            composable("wallet") { WalletScreen(onRedeemClick = { navController.navigate("redeem") }) }
            composable("task") { TaskScreen() }
            composable("game") { GameScreen(onPlayClick = { /* TODO: launch game */ }) }
            composable("redeem") { RedeemScreen() }
            composable("tournament") { TournamentScreen() }
            composable("profile") { ProfileScreen() }
        }
    }
}
