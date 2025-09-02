package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tapnexempire.screens.HomeScreen
import com.tapnexempire.screens.PlaceholderScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "auth"
    ) {
        composable("auth") { PlaceholderScreen("Auth Screen") }
        composable("home") { HomeScreen() }
    }
}
