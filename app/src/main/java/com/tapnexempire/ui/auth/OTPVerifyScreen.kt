package com.tapnexempire.ui.auth

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun OTPVerifyScreen(
    navController: NavHostController,
    verificationId: String
) {

    Button(onClick = {

        navController.navigate("home") {
            popUpTo("otp_login") { inclusive = true }
        }

    }) {
        Text("Verify OTP")
    }
}
