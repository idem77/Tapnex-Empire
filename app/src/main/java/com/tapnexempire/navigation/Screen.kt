package com.tapnexempire.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Auth : Screen("auth")
    object Otp : Screen("otp")
    object Home : Screen("home")
    object Wallet : Screen("wallet")
    object Recharge : Screen("recharge")
    object Withdraw : Screen("withdraw")
    object TransactionHistory : Screen("transaction_history")
    object Task : Screen("task")
    object Tournament : Screen("tournament")
}
