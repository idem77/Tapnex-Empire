package com.tapnexempire.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun AdminDashboardScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "🛠 Admin Control Center",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 👤 USERS
        AdminCard(
            title = "User Management",
            subtitle = "Ban users, manage coins, view stats",
            onClick = { navController.navigate("user_management") }
        )

        // 🏆 TOURNAMENT
        AdminCard(
            title = "Tournament Control",
            subtitle = "Create, close, delete tournaments",
            onClick = { navController.navigate("tournament_control") }
        )

        // 💰 DEPOSIT
        AdminCard(
            title = "Deposit Requests",
            subtitle = "Approve or reject deposits",
            onClick = { navController.navigate("deposit_requests") }
        )

        // 💸 WITHDRAW
        AdminCard(
            title = "Withdraw Requests",
            subtitle = "Handle withdrawal approvals",
            onClick = { navController.navigate("withdraw_requests") }
        )

        // 🎮 EVENTS
        AdminCard(
            title = "Event Control",
            subtitle = "Manage in-game events",
            onClick = { navController.navigate("event_control") }
        )

        // 🎁 BUNDLES (FROZEN)
        AdminCard(
            title = "Bundle Control (Frozen)",
            subtitle = "Currently disabled system",
            onClick = { navController.navigate("bundle_control") }
        )

        // 🧍 CHARACTER
        AdminCard(
            title = "Character Control",
            subtitle = "Manage game characters/items",
            onClick = { navController.navigate("character_control") }
        )
    }
}
