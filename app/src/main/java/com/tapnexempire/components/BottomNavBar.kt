package com.tapnexempire.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.unit.dp
import com.tapnexempire.ui.theme.RoyalTeal
import com.tapnexempire.ui.theme.VibrantCoral

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    object Home : BottomNavItem("home", "Home", Icons.Default.Home)
    object Wallet : BottomNavItem("wallet", "Wallet", Icons.Default.AccountBalanceWallet)
    object Task : BottomNavItem("task", "Tasks", Icons.Default.Star)
    object Game : BottomNavItem("game", "Games", Icons.Default.VideogameAsset)
    object Redeem : BottomNavItem("redeem", "Redeem", Icons.Default.Redeem)
    object Tournament : BottomNavItem("tournament", "Tournaments", Icons.Default.EmojiEvents)
    object Profile : BottomNavItem("profile", "Profile", Icons.Default.Person)
}

private val bottomNavItems = listOf(
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
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = RoyalTeal,
        tonalElevation = 6.dp
    ) {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            // pop up to the start destination to avoid building a deep back stack
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = VibrantCoral,
                    selectedTextColor = VibrantCoral,
                    unselectedIconColor = LocalContentColor.current.copy(alpha = 0.6f),
                    unselectedTextColor = LocalContentColor.current.copy(alpha = 0.6f)
                )
            )
        }
    }
}
