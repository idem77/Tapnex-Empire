package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tapnexempire.screen.*

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            PlaceholderScreen("Splash")
        }
        composable("home") {
            HomeScreen(
                onGameClick = { navController.navigate("games") },
                onWalletClick = { navController.navigate("wallet") },
                onRedeemClick = { navController.navigate("redeem") },
                onHelpClick = { navController.navigate("help") },
                onProfileClick = { navController.navigate("profile") }
            )
        }
        composable("games") {
            GameScreen()
        }
        composable("wallet") {
            PlaceholderScreen("Wallet")
        }
        composable("redeem") {
            PlaceholderScreen("Redeem")
        }
        composable("help") {
            HelpScreen(
                onFaqClick = { navController.navigate("faq") },
                onContactClick = { navController.navigate("contact") }
            )
        }
        composable("profile") {
            PlaceholderScreen("Profile")
        }
        composable("faq") {
            PlaceholderScreen("FAQ")
        }
        composable("contact") {
            PlaceholderScreen("Contact Us")
        }
    }
}
