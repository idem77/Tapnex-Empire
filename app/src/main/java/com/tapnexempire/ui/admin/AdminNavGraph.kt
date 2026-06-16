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
            AdminDashboardScreen(navController)
        }

        composable("user_management") {
            UserManagementScreen()
        }

        composable("tournament_control") {

    TournamentControlScreen(
        navController = navController
    )
        }

        composable("deposit_requests") {
            DepositRequestScreen()
        }

        composable("withdraw_requests") {
            WithdrawRequestScreen()
        }

        composable("event_control") {
            EventControlScreen()
        }

        composable("bundle_control") {
            BundleControlScreen()
        }

        composable("character_control") {
            CharacterControlScreen()
        }
    }
}
