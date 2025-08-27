package com.tapnexempire.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tapnexempire.screen.*
import com.tapnexempire.components.TopBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.Person

sealed class Screen(val route: String, val title: String) {
    data object Splash : Screen("splash", "Splash")
    data object Home : Screen("home", "Home")
    data object Wallet : Screen("wallet", "Wallet")
    data object Games : Screen("games", "Games")
    data object Profile : Screen("profile", "Profile")
    // extra routes (no bottom tab)
    data object Tasks : Screen("tasks", "Tasks")
    data object Redeem : Screen("redeem", "Redeem")
    data object Help : Screen("help", "Help")
    data object Offers : Screen("offers", "Offers")
    data object Settings : Screen("settings", "Settings")
}

data class BottomItem(
    val screen: Screen,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val bottomItems = listOf(
        BottomItem(Screen.Home, Icons.Filled.Home),
        BottomItem(Screen.Wallet, Icons.Filled.Wallet),
        BottomItem(Screen.Games, Icons.Filled.SportsEsports),
        BottomItem(Screen.Profile, Icons.Filled.Person)
    )

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val showTopBar = currentRoute != Screen.Splash.route
    val showBottomBar = currentRoute in bottomItems.map { it.screen.route }

    Scaffold(
        topBar = {
            if (showTopBar) {
                TopBar(
                    title = when (currentRoute) {
                        Screen.Home.route -> Screen.Home.title
                        Screen.Wallet.route -> Screen.Wallet.title
                        Screen.Games.route -> Screen.Games.title
                        Screen.Profile.route -> Screen.Profile.title
                        Screen.Tasks.route -> Screen.Tasks.title
                        Screen.Redeem.route -> Screen.Redeem.title
                        Screen.Help.route -> Screen.Help.title
                        Screen.Offers.route -> Screen.Offers.title
                        Screen.Settings.route -> Screen.Settings.title
                        else -> "Tapnex Empire"
                    },
                    canNavigateBack = navController.previousBackStackEntry != null &&
                            currentRoute !in listOf(Screen.Home.route, Screen.Wallet.route, Screen.Games.route, Screen.Profile.route),
                    onBack = { navController.popBackStack() }
                )
            }
        },
        bottomBar = {
            if (showBottomBar) {
                BottomNavBar(navController, bottomItems, currentRoute)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) { SplashScreen(onFinished = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            }) }
            composable(Screen.Home.route) { HomeScreen(
                goToWallet = { navController.safeNavigate(Screen.Wallet.route) },
                goToGames = { navController.safeNavigate(Screen.Games.route) },
                goToTasks = { navController.safeNavigate(Screen.Tasks.route) },
                goToRedeem = { navController.safeNavigate(Screen.Redeem.route) },
                goToHelp = { navController.safeNavigate(Screen.Help.route) },
                goToOffers = { navController.safeNavigate(Screen.Offers.route) }
            ) }
            composable(Screen.Wallet.route) { WalletScreen(goToRedeem = { navController.safeNavigate(Screen.Redeem.route) }) }
            composable(Screen.Games.route) { GameScreen() }
            composable(Screen.Profile.route) { ProfileScreen(goToSettings = { navController.safeNavigate(Screen.Settings.route) }) }

            // extra
            composable(Screen.Tasks.route) { TaskScreen() }
            composable(Screen.Redeem.route) { RedeemScreen() }
            composable(Screen.Help.route) { HelpScreen() }
            composable(Screen.Offers.route) { OffersScreen() }
            composable(Screen.Settings.route) { SettingsScreen() }
        }
    }
}

@Composable
private fun BottomNavBar(
    navController: NavHostController,
    items: List<BottomItem>,
    currentRoute: String?
) {
    NavigationBar {
        items.forEach { item ->
            val selected = currentRoute == item.screen.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.screen.title) },
                label = { Text(item.screen.title) }
            )
        }
    }
}

private fun NavHostController.safeNavigate(route: String) {
    if (currentDestination?.route == route) return
    navigate(route)
}
