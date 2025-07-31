package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tapnexempire.screens.HomeScreen
import com.tapnexempire.screens.WalletScreen
import com.tapnexempire.screens.SplashScreen

sealed class Screen(val route: String) {
        object Splash : Screen("splash")
            object Home : Screen("home")
                object Wallet : Screen("wallet")
}

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
        NavHost(navController = navController, startDestination = Screen.Splash.route) {
                    composable(Screen.Splash.route) {
                                    SplashScreen(navController)
                    }
                            composable(Screen.Home.route) {
                                            HomeScreen(navController)
                            }
                                    composable(Screen.Wallet.route) {
                                                    WalletScreen(navController)
                                    }
        }
}
                                    }
                            }
                    }
        }
}
}