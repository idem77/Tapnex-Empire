package com.tapnexempire.ui.auth

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun OTPLoginScreen(navController: NavHostController) {

    Button(onClick = {

        val verificationId = "test123" // firebase बाद में देगा

        navController.navigate("otp_verify/$verificationId")

    }) {
        Text("Send OTP")
    }
}
