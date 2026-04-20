package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.google.firebase.auth.FirebaseAuth
import com.tapnexempire.ui.auth.OTPScreen
import com.tapnexempire.ui.auth.OTPVerifyScreen
import com.tapnexempire.ui.splash.SplashScreen
import com.tapnexempire.ui.home.HomeScreen
import com.tapnexempire.ui.task.TaskScreen
import com.tapnexempire.ui.tournament.TournamentListScreen
import com.tapnexempire.ui.tournament.detail.TournamentDetailScreen
import com.tapnexempire.ui.wallet.WalletScreen
import com.tapnexempire.ui.wallet.WithdrawScreen
import com.tapnexempire.viewmodel.TournamentViewModel
import com.tapnexempire.viewmodel.WalletViewModel

object Routes {
    const val SPLASH = "splash"
    const val OTP_LOGIN = "otp_login"
    const val OTP_VERIFY = "otp_verify"

    const val HOME = "home"
    const val WALLET = "wallet"
    const val TASKS = "tasks"
    const val TOURNAMENTS = "tournaments"
    const val TOURNAMENT_DETAIL = "tournament_detail"
    const val WITHDRAW = "withdraw"
}

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH,
        modifier = modifier
    ) {

        // 🔥 Splash
        composable(Routes.SPLASH) {
            SplashScreen(navController)
        }

        // 🔐 OTP Login
        composable(Routes.OTP_LOGIN) {
            OTPScreen(navController)
        }

        // 🔐 OTP Verify
        composable(
            route = "${Routes.OTP_VERIFY}/{verificationId}",
            arguments = listOf(
                navArgument("verificationId") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val verificationId =
                backStackEntry.arguments?.getString("verificationId") ?: ""

            OTPVerifyScreen(
                navController = navController,
                verificationId = verificationId
            )
        }

        // 🔹 Home
        composable(Routes.HOME) {
            HomeScreen()
        }

        // 💰 Wallet (REAL USER ID)
        composable(Routes.WALLET) {
            val walletViewModel: WalletViewModel = hiltViewModel()

            WalletScreen(
                viewModel = walletViewModel,
                userId = userId,
                onTransactionClick = { }
            )
        }

        // 🔹 Tasks
        composable(Routes.TASKS) {
            TaskScreen()
        }

        // 🏆 Tournament List
        composable(Routes.TOURNAMENTS) {
            val tournamentViewModel: TournamentViewModel = hiltViewModel()

            TournamentListScreen(
                viewModel = tournamentViewModel,
                userId = userId
            )
        }

        // 🔹 Tournament Detail
        composable(
            route = "${Routes.TOURNAMENT_DETAIL}/{tournamentId}",
            arguments = listOf(
                navArgument("tournamentId") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val tournamentId =
                backStackEntry.arguments?.getString("tournamentId") ?: ""

            TournamentDetailScreen(tournamentId = tournamentId)
        }

        // 💸 Withdraw
        composable(Routes.WITHDRAW) {
            WithdrawScreen()
        }
    }
}
