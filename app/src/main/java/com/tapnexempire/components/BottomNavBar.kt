package com.tapnexempire.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

data class BottomNavItem(val route: String, val label: String, val icon: ImageVector)

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("wallet", "Wallet", Icons.Default.AccountBalanceWallet),
        BottomNavItem("games", "Games", Icons.Default.SportsEsports),
        BottomNavItem("offers", "Offers", Icons.Default.LocalOffer),
        BottomNavItem("tasks", "Tasks", Icons.Default.CheckCircle),
        BottomNavItem("rewards", "Rewards", Icons.Default.Star),
        BottomNavItem("redeem", "Redeem", Icons.Default.Money),
        BottomNavItem("profile", "Profile", Icons.Default.Person),
        BottomNavItem("help", "Help", Icons.Default.Help)
    )

    NavigationBar {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo("home") { inclusive = false }
                        launchSingleTop = true
                    }
                },
                label = { Text(item.label) },
                icon = { Icon(item.icon, contentDescription = item.label) }
            )
        }
    }
}
