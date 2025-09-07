package com.tapnexempire.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tapnexempire.screen.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen(
                    onWalletClick = { navController.navigate("wallet") },
                    onGameClick = { navController.navigate("game") }
                )
            }
            composable("wallet") {
                WalletScreen(onRedeemClick = { navController.navigate("redeem") })
            }
            composable("task") {
                TaskScreen(onTaskClick = { /* future logic */ })
            }
            composable("game") {
                GameScreen(onPlayClick = { /* play logic */ })
            }
            composable("redeem") {
                RedeemScreen(onBackClick = { navController.popBackStack() })
            }
            composable("tournament") {
                TournamentScreen(onBackClick = { navController.popBackStack() })
            }
            composable("profile") {
                ProfileScreen(onBackClick = { navController.popBackStack() })
            }
            composable("splash") {
                SplashScreen(onTimeout = { navController.navigate("auth") })
            }
            composable("auth") {
                AuthScreen(onContinue = { navController.navigate("home") })
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("Home", "home", Icons.Default.Home),
        BottomNavItem("Wallet", "wallet", Icons.Default.AccountBalanceWallet),
        BottomNavItem("Tasks", "task", Icons.Default.List),
        BottomNavItem("Tournament", "tournament", Icons.Default.EmojiEvents),
        BottomNavItem("Profile", "profile", Icons.Default.Person),
    )

    NavigationBar {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

data class BottomNavItem(val label: String, val route: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)
