package com.tapnexempire.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.unit.dp

// Import your screens (adjust package names as your project)
import com.tapnexempire.ui.splash.SplashScreen
import com.tapnexempire.ui.auth.LoginScreen
import com.tapnexempire.ui.auth.OtpVerificationScreen
import com.tapnexempire.ui.auth.SignupScreen
import com.tapnexempire.ui.home.HomeScreen
import com.tapnexempire.ui.wallet.WalletScreen
import com.tapnexempire.ui.wallet.RechargeScreen
import com.tapnexempire.ui.wallet.WithdrawScreen
import com.tapnexempire.ui.wallet.TransactionHistoryScreen
import com.tapnexempire.ui.tournament.TournamentListScreen
import com.tapnexempire.ui.tournament.TournamentDetailScreen
import com.tapnexempire.ui.task.TaskScreen
import com.tapnexempire.ui.profile.ProfileScreen
import com.tapnexempire.ui.game.LudoScreen

// Components
import com.tapnexempire.ui.components.AppBottomBar
import com.tapnexempire.ui.components.BottomNavItem
import com.tapnexempire.ui.components.AppTopBar

@Composable
fun RootNavGraph(
    startDestination: String = Screen.Splash.route
) {
    val navController = rememberNavController()
    AppNavGraph(navController = navController, startDestination = startDestination)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Splash.route
) {
    // Routes on which we show bottom navigation
    val bottomBarRoutes = listOf(
        Screen.Home.route,
        Screen.Wallet.route,
        Screen.Tasks.route,
        Screen.TournamentList.route
    )

    // Observe current back stack to get current route
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    // Bottom nav items (icons: use material icons when creating BottomNavItem)
    // Import appropriate ImageVector icons where you call this (e.g., Icons.Default.Home)
    val bottomItems = remember {
        listOf(
            BottomNavItem(label = "Home", icon = androidx.compose.material.icons.Icons.Default.Home, route = Screen.Home.route),
            BottomNavItem(label = "Wallet", icon = androidx.compose.material.icons.Icons.Default.Wallet, route = Screen.Wallet.route),
            BottomNavItem(label = "Tasks", icon = androidx.compose.material.icons.Icons.Default.Task, route = Screen.Tasks.route),
            BottomNavItem(label = "Tournaments", icon = androidx.compose.material.icons.Icons.Default.AccountCircle, route = Screen.TournamentList.route)
        )
    }

    // Scaffold with conditional top/bottom bars
    Scaffold(
        topBar = {
            // Simple TopBar: show on Home/Wallet/Tournament/Tasks/Profile (adjust as you like)
            if (currentRoute in listOf(Screen.Home.route, Screen.Wallet.route, Screen.TournamentList.route, Screen.Tasks.route, Screen.Profile.route)) {
                AppTopBar(
                    title = when (currentRoute) {
                        Screen.Home.route -> "Tapnex Empire"
                        Screen.Wallet.route -> "My Wallet"
                        Screen.Tasks.route -> "Tasks"
                        Screen.TournamentList.route -> "Tournaments"
                        Screen.Profile.route -> "Profile"
                        else -> ""
                    },
                    navigationIcon = null,
                    onNavigationClick = null
                )
            }
        },
        bottomBar = {
            if (currentRoute in bottomBarRoutes) {
                AppBottomBar(
                    items = bottomItems,
                    currentRoute = currentRoute ?: Screen.Home.route,
                    onItemClick = { item ->
                        // Navigate safely to avoid multiple copies in backstack
                        if (currentRoute != item.route) {
                            navController.navigate(item.route) {
                                popUpTo(Screen.Home.route) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) { SplashScreen(navController) }

            composable(Screen.Login.route) { LoginScreen(navController) }
            composable(Screen.OTP.route) { OtpVerificationScreen(navController) }
            composable(Screen.Signup.route) { SignupScreen(navController) }

            composable(Screen.Home.route) { HomeScreen(navController) }
            composable(Screen.Wallet.route) { WalletScreen(navController) }
            composable(Screen.Recharge.route) { RechargeScreen(navController) }
            composable(Screen.Withdraw.route) { WithdrawScreen(navController) }
            composable(Screen.History.route) { TransactionHistoryScreen(navController) }

            composable(Screen.TournamentList.route) { TournamentListScreen(navController) }
            composable("tournament/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id") ?: ""
                TournamentDetailScreen(navController, id)
            }

            composable(Screen.Tasks.route) { TaskScreen(navController) }
            composable(Screen.Profile.route) { ProfileScreen(navController) }
            composable(Screen.Ludo.route) { LudoScreen(navController) }
        }
    }
}
