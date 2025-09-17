package com.tapnexempire.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object OtpVerification : Screen("otp_verification/{phoneNumber}") {
        fun createRoute(phoneNumber: String) = "otp_verification/$phoneNumber"
    }
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Wallet : Screen("wallet")
    object Recharge : Screen("recharge")
    object Withdraw : Screen("withdraw")
    object Tournament : Screen("tournament")
}
