package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tapnexempire.repository.AuthRepository
import com.tapnexempire.screens.auth.AuthScreen
import com.tapnexempire.screens.common.PlaceholderScreen
import com.tapnexempire.screens.home.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    val repo = AuthRepository()
    val start = if (repo.isUserLoggedIn()) "home" else "auth"

    NavHost(navController = navController, startDestination = start) {
        composable("auth") { AuthScreen(navController) }
        composable("home") { HomeScreen() }
        composable("games") { PlaceholderScreen("Games") }
        composable("wallet") { PlaceholderScreen("Wallet") }
        composable("redeem") { PlaceholderScreen("Redeem") }
        composable("help") { PlaceholderScreen("Help") }
        composable("profile") { PlaceholderScreen("Profile") }
        // add other routes as PlaceholderScreen until you implement them
    }
}
