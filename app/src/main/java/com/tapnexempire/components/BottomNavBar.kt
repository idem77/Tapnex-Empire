package com.tapnexempire.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.tapnexempire.ui.theme.RoyalTeal
import com.tapnexempire.ui.theme.VibrantCoral

sealed class BottomNavItem(val route: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object Home : BottomNavItem("home", "Home", Icons.Default.Home)
    object Wallet : BottomNavItem("wallet", "Wallet", Icons.Default.AccountBalanceWallet)
    object Task : BottomNavItem("task", "Tasks", Icons.Default.Star)
    object Game : BottomNavItem("game", "Games", Icons.Default.VideogameAsset)
    object Redeem : BottomNavItem("redeem", "Redeem", Icons.Default.Redeem)
    object Tournament : BottomNavItem("tournament", "Tournaments", Icons.Default.EmojiEvents)
    object Profile : BottomNavItem("profile", "Profile", Icons.Default.Person)
}

val bottomNavItems = listOf(
    BottomNavItem.Home,
    BottomNavItem.Wallet,
    BottomNavItem.Task,
    BottomNavItem.Game,
    BottomNavItem.Redeem,
    BottomNavItem.Tournament,
    BottomNavItem.Profile
)

@Composable
fun BottomNavBar(navController: NavHostController) {
    NavigationBar(containerColor = RoyalTeal) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo("home") { inclusive = false }
                        launchSingleTop = true
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = VibrantCoral,
                    selectedTextColor = VibrantCoral,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}
