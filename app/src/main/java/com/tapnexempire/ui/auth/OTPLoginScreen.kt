package com.tapnexempire.ui.auth

import android.app.Activity
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
import androidx.compose.ui.platform.LocalContext

@Composable
fun OTPLoginScreen(navController: NavHostController) {

    // जब OTP send हो जाए
    val verificationId = "dummy_verification" // (firebase से आएगा बाद में)

    Button(onClick = {
        navController.navigate("otp_verify/$verificationId")
    }) {
        Text("Send OTP")
    }
}
