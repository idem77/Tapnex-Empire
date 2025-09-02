package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tapnexempire.repository.AuthRepository
import com.tapnexempire.screens.auth.AuthScreen
import com.tapnexempire.screens.home.HomeScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    val repo = AuthRepository()
    val startDestination = if (repo.isUserLoggedIn()) "home" else "auth"

    NavHost(navController = navController, startDestination = startDestination) {
        composable("auth") { AuthScreen(navController) }
        composable("home") { HomeScreen() }
    }
}
