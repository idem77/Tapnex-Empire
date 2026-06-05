package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.firebase.auth.FirebaseAuth
import com.tapnexempire.ui.auth.LoginScreen
import com.tapnexempire.ui.auth.SignupScreen
import com.tapnexempire.ui.home.HomeScreen
import com.tapnexempire.ui.splash.SplashScreen
import com.tapnexempire.ui.task.TaskScreen
import com.tapnexempire.ui.tournament.TournamentListScreen
import com.tapnexempire.ui.game.GameScreen
import com.tapnexempire.ui.equipment.EquipmentScreen
import com.tapnexempire.ui.tournament.detail.TournamentDetailScreen
import com.tapnexempire.ui.wallet.DepositScreen
import com.tapnexempire.ui.wallet.TransactionHistoryScreen
import com.tapnexempire.ui.wallet.WalletScreen
import com.tapnexempire.ui.wallet.WithdrawScreen
import com.tapnexempire.viewmodel.TournamentViewModel
import com.tapnexempire.viewmodel.WalletViewModel
import com.tapnexempire.ui.admin.AdminDashboardScreen
import com.tapnexempire.ui.admin.DepositRequestScreen
import com.tapnexempire.ui.admin.WithdrawRequestScreen
import com.tapnexempire.ui.admin.TournamentControlScreen
import com.tapnexempire.ui.admin.BundleControlScreen
import com.tapnexempire.ui.admin.CharacterControlScreen
import com.tapnexempire.ui.admin.EventControlScreen
import com.tapnexempire.ui.admin.UserManagementScreen

object Routes {

    const val SPLASH = "splash"

    const val LOGIN = "login"

    const val SIGNUP = "signup"

    const val HOME = "home"

    const val WALLET = "wallet"

    const val TASKS = "tasks"

    const val TOURNAMENTS = "tournaments"

    const val TOURNAMENT_DETAIL = "tournament_detail"

    const val WITHDRAW = "withdraw"

    const val DEPOSIT = "deposit"

    const val TRANSACTIONS = "transactions"
    
    const val GAME = "game"

    const val EQUIPMENT = "equipment"

    const val ADMIN = "admin"

const val DEPOSIT_REQUESTS =
"deposit_requests"

const val WITHDRAW_REQUESTS =
"withdraw_requests"

const val TOURNAMENT_CONTROL =
"tournament_control"

const val BUNDLE_CONTROL =
"bundle_control"

const val CHARACTER_CONTROL =
"character_control"

const val EVENT_CONTROL =
"event_control"

const val USER_MANAGEMENT =
"user_management"
    
}

@Composable
fun AppNavGraph(

    navController: NavHostController,

    modifier: Modifier = Modifier
) {

    NavHost(

        navController = navController,

        startDestination = Routes.SPLASH,

        modifier = modifier
    ) {

        // 🔥 Splash
        composable(Routes.SPLASH) {

            SplashScreen(navController)
        }

        // 🔐 Login
        composable(Routes.LOGIN) {

            LoginScreen(

                onLoginSuccess = {

                    navController.navigate(Routes.HOME) {

                        popUpTo(Routes.LOGIN) {
                            inclusive = true
                        }
                    }
                },

                goToSignup = {

                    navController.navigate(Routes.SIGNUP)
                }
            )
        }

        // 🆕 Signup
        composable(Routes.SIGNUP) {

            SignupScreen(

                onSignupSuccess = {

                    navController.navigate(Routes.HOME) {

                        popUpTo(Routes.SIGNUP) {
                            inclusive = true
                        }
                    }
                },

                goToLogin = {

                    navController.navigate(Routes.LOGIN)
                }
            )
        }

        // 🏠 Home
composable(Routes.HOME) {

    HomeScreen(
        navController = navController
    )
}
        // 💰 Wallet
        composable(Routes.WALLET) {

            val walletViewModel: WalletViewModel =
                hiltViewModel()

            WalletScreen(

                viewModel = walletViewModel,

                userId = FirebaseAuth
                    .getInstance()
                    .currentUser
                    ?.uid ?: "",

                navController = navController,

                onTransactionClick = {

                    navController.navigate(
                        Routes.TRANSACTIONS
                    )
                }
            )
        }

        // 🔹 Tasks
        composable(Routes.TASKS) {

            TaskScreen()
        }

          //🧸Equipment screen 
            composable(
    Routes.EQUIPMENT
) {

    EquipmentScreen()
            }

        // 🏆 Tournament List
composable(Routes.TOURNAMENTS) {

    val tournamentViewModel:
            TournamentViewModel =
        hiltViewModel()

    TournamentListScreen(

        navController = navController,

        viewModel = tournamentViewModel,

        userId = FirebaseAuth
            .getInstance()
            .currentUser
            ?.uid ?: ""
    )
}

// 🔹 Tournament Detail
composable(

    route =
        "${Routes.TOURNAMENT_DETAIL}/{tournamentId}",

    arguments = listOf(

        navArgument("tournamentId") {

            type = NavType.StringType
        }
    )

) { backStackEntry ->

    val tournamentId =

        backStackEntry
            .arguments
            ?.getString("tournamentId") ?: ""

    TournamentDetailScreen(

        navController = navController,
        
        tournamentId = tournamentId,

        userId = FirebaseAuth
            .getInstance()
            .currentUser
            ?.uid ?: "",

        entryFee = 240
    )
}
          //🎮Game
            composable("${Routes.GAME}/{gameUrl}") {

    backStackEntry ->

    val gameUrl =

        backStackEntry
            .arguments
            ?.getString("gameUrl") ?: ""

    GameScreen(
        gameUrl = gameUrl
    )
            }

        // 📜 Transactions
        composable(Routes.TRANSACTIONS) {

            TransactionHistoryScreen(

                userId = FirebaseAuth
                    .getInstance()
                    .currentUser
                    ?.uid ?: ""
            )
        }

        // 💸 Withdraw
        composable(Routes.WITHDRAW) {

            WithdrawScreen()
        }

       // 💰 Deposit
composable(Routes.DEPOSIT) {

    val viewModel: WalletViewModel =
        hiltViewModel()

    DepositScreen(

        onBack = {

            navController.popBackStack()
        },

        onProceed = { coins ->

            viewModel.addCoins(coins)

            navController.popBackStack()
        }
    )
      }         
    }
}
