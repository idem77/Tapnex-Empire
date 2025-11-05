package com.tapnexempire.ui.auth

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.ui.theme.PinkPeachLight
import com.tapnexempire.viewmodel.AuthViewModel

@Composable
fun OtpVerificationScreen(
    authViewModel: AuthViewModel,
    onLoginSuccess: () -> Unit
) {
    val context = LocalContext.current as Activity
    val otpCode by authViewModel.otpCode
    val isLoading by authViewModel.isLoading
    val errorMessage by authViewModel.errorMessage

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PinkPeachLight),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = "Verify OTP",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = otpCode,
                onValueChange = { authViewModel.otpCode.value = it },
                label = { Text("Enter OTP Code") },
                singleLine = true,
                keyboardOptions = androidx.compose.ui.text.input.KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    authViewModel.verifyOtp(context, onSuccess = onLoginSuccess)
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(22.dp)
                    )
                } else {
                    Text("Verify OTP", fontSize = 18.sp)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (errorMessage != null) {
                Text(
                    text = errorMessage ?: "",
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 14.sp
                )
            }
        }
    }
}
