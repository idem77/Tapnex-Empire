package com.tapnexempire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tapnexempire.ui.auth.OtpLoginScreen
import com.tapnexempire.ui.auth.OtpVerificationScreen
import com.tapnexempire.ui.home.HomeScreen
import com.tapnexempire.ui.wallet.WalletScreen
import com.tapnexempire.ui.wallet.WithdrawScreen
import com.tapnexempire.ui.wallet.TransactionHistoryScreen
import com.tapnexempire.ui.splash.SplashScreen

object Routes {
    const val SPLASH = "splash"
    const val LOGIN = "login"
    const val OTP = "otp"
    const val HOME = "home"

    const val WALLET = "wallet"
    const val WITHDRAW = "withdraw"
    const val TRANSACTIONS = "transactions"

    const val TOURNAMENT_LIST = "tournaments"
    const val TOURNAMENT_DETAIL = "tournament_detail"
    const val MY_TOURNAMENTS = "my_tournaments"

    const val PROFILE = "profile"
    const val EDIT_PROFILE = "edit_profile"
    const val SETTINGS = "settings"
}   @Composable
fun AppNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {

        composable(Routes.SPLASH) {
            SplashScreen {
                navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.SPLASH) { inclusive = true }
                }
            }
        }

        composable(Routes.LOGIN) {
            OtpLoginScreen(
                onOtpSent = {
                    navController.navigate(Routes.OTP)
                }
            )
        }

        composable(Routes.OTP) {
            OtpVerificationScreen(
                onVerified = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.HOME) {
            HomeScreen(
                onWalletClick = {
                    navController.navigate(Routes.WALLET)
                },
                onTournamentClick = {
                    navController.navigate(Routes.TOURNAMENT_LIST)
                },
                onProfileClick = {
                    navController.navigate(Routes.PROFILE)
                }
            )
        }

        // 🏆 Tournament
        composable(Routes.TOURNAMENT_LIST) {
            TournamentListScreen(
                onTournamentClick = {
                    navController.navigate(Routes.TOURNAMENT_DETAIL)
                },
                onMyTournamentClick = {
                    navController.navigate(Routes.MY_TOURNAMENTS)
                }
            )
        }

        composable(Routes.TOURNAMENT_DETAIL) {
            TournamentDetailScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.MY_TOURNAMENTS) {
            MyTournamentsScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // 👤 Profile
        composable(Routes.PROFILE) {
            ProfileScreen(
                onEditProfileClick = {
                    navController.navigate(Routes.EDIT_PROFILE)
                },
                onSettingsClick = {
                    navController.navigate(Routes.SETTINGS)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.EDIT_PROFILE) {
            EditProfileScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.SETTINGS) {
            SettingsScreen(
                onLogout = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // 💰 Wallet
        composable(Routes.WALLET) {
            WalletScreen(
                onWithdrawClick = {
                    navController.navigate(Routes.WITHDRAW)
                },
                onTransactionClick = {
                    navController.navigate(Routes.TRANSACTIONS)
                }
            )
        }

        composable(Routes.WITHDRAW) {
            WithdrawScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.TRANSACTIONS) {
            TransactionHistoryScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
