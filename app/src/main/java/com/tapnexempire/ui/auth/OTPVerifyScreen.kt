package com.tapnexempire.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.viewmodel.AuthViewModel

@Composable
fun OTPVerifyScreen(
    navController: NavHostController,
    verificationId: String
) {

    Button(onClick = {

        // login success
        navController.navigate("home") {
            popUpTo("otp_login") { inclusive = true }
        }

    }) {
        Text("Verify OTP")
    }
}
