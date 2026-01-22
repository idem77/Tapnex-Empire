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
fun OtpVerifyScreen(
    verificationId: String,
    onLoginSuccess: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    var otp by remember { mutableStateOf("") }

    val authSuccess by viewModel.authSuccess.collectAsState()

    LaunchedEffect(authSuccess) {
        if (authSuccess) {
            onLoginSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Enter OTP", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = otp,
            onValueChange = {
                if (it.length <= 6) otp = it
            },
            label = { Text("6-digit OTP") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.verifyOtp(
                    verificationId = verificationId,
                    otp = otp
                )
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = otp.length == 6
        ) {
            Text("Verify OTP")
        }
    }
}
