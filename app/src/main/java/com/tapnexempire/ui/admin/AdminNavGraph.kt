package com.tapnexempire.ui.admin

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AdminNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "admin_dashboard"
    ) {

        composable("admin_dashboard") {
            AdminDashboardScreen(navController = navController)
        }

        composable("user_management") {
            UserManagementScreen(navController = navController)
        }

        composable("tournament_control") {
            TournamentControlScreen(navController = navController)
        }

        composable("deposit_requests") {
            DepositRequestScreen(navController = navController)
        }

        composable("withdraw_requests") {
            WithdrawRequestScreen(navController = navController)
        }

        composable("event_control") {
            EventControlScreen(navController = navController)
        }

        composable("bundle_control") {
            BundleControlScreen(navController = navController)
        }

        composable("character_control") {
            CharacterControlScreen(navController = navController)
        }
    }
}
