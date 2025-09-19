package com.tapnexempire.navigation

sealed class Screen(val route: String) {

    // 🔹 Auth Screens
    object Splash : Screen("splash")
    object Login : Screen("login")
    object OtpVerification : Screen("otp_verification/{phoneNumber}") {
        fun createRoute(phoneNumber: String) = "otp_verification/$phoneNumber"
    }

    // 🔹 Main Screens
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Wallet : Screen("wallet")

    // 🔹 Transactions
    object Recharge : Screen("recharge")
    object Withdraw : Screen("withdraw")

    // 🔹 Game / Tournament
    object Tournament : Screen("tournament")
    object TournamentList : Screen("tournament_list")
    object Ludo : Screen("ludo")

    // 🔹 Others
    object Tasks : Screen("tasks")
}
