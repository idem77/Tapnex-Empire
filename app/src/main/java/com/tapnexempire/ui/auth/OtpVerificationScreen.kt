package com.tapnexempire.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.viewmodel.AuthViewModel

@Composable
fun OtpVerificationScreen(
    verificationId: String,
    onSuccess: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    var otp by remember { mutableStateOf("") }
    val isSuccess by viewModel.authSuccess.collectAsState()

    LaunchedEffect(isSuccess) {
        if (isSuccess) onSuccess()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Verify OTP", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = otp,
            onValueChange = { otp = it },
            label = { Text("OTP") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.verifyOtp(verificationId, otp)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Verify")
        }
    }
}
