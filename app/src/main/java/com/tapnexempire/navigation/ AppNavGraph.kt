package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tapnexempire.ui.auth.LoginScreen
import com.tapnexempire.ui.auth.OtpVerificationScreen
import com.tapnexempire.ui.auth.SignupScreen
import com.tapnexempire.ui.game.LudoScreen
import com.tapnexempire.ui.home.HomeScreen
import com.tapnexempire.ui.splash.SplashScreen
import com.tapnexempire.ui.task.TaskScreen
import com.tapnexempire.ui.tournament.TournamentDetailScreen
import com.tapnexempire.ui.tournament.TournamentListScreen
import com.tapnexempire.ui.wallet.RechargeScreen
import com.tapnexempire.ui.wallet.TransactionHistoryScreen
import com.tapnexempire.ui.wallet.WalletScreen
import com.tapnexempire.ui.wallet.WithdrawScreen
import com.tapnexempire.ui.profile.ProfileScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object OTP : Screen("otp")
    object Signup : Screen("signup")
    object Home : Screen("home")
    object Wallet : Screen("wallet")
    object Recharge : Screen("recharge")
    object Withdraw : Screen("withdraw")
    object History : Screen("history")
    object TournamentList : Screen("tournaments")
    object TournamentDetail : Screen("tournament/{id}") {
        fun createRoute(id: String) = "tournament/$id"
    }
    object Tasks : Screen("tasks")
    object Profile : Screen("profile")
    object Ludo : Screen("ludo")
}

@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Screen.Splash.route, modifier = modifier) {
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
        composable("tournament/{id}") { backEntry ->
            val id = backEntry.arguments?.getString("id") ?: ""
            TournamentDetailScreen(navController, id)
        }
        composable(Screen.Tasks.route) { TaskScreen(navController) }
        composable(Screen.Profile.route) { ProfileScreen(navController) }
        composable(Screen.Ludo.route) { LudoScreen(navController) }
    }
}
