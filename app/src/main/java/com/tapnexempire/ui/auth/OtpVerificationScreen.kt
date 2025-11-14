package com.tapnexempire.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.tapnexempire.viewmodel.AuthViewModel

@Composable
fun OtpVerificationScreen(
    viewModel: AuthViewModel,
    onSuccess: () -> Unit
) {
    var otp by remember { mutableStateOf("") }

    val isVerified = viewModel.isVerified.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Enter the OTP sent to your phone", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = otp,
            onValueChange = { otp = it },
            label = { Text("Enter OTP") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { viewModel.verifyOtp(otp) }) {
            Text("Verify OTP")
        }

        LaunchedEffect(isVerified) {
            if (isVerified) {
                onSuccess()
            }
        }
    }
}
