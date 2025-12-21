package com.tapnexempire.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.ui.theme.*
import com.tapnexempire.viewmodel.AuthViewModel

@Composable
fun OtpVerificationScreen(
    phoneNumber: String,
    onVerified: () -> Unit
) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val otpState = authViewModel.otpState.collectAsState().value
    var otp by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightCream)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Enter OTP sent to $phoneNumber",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = CharcoalBlack
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = otp,
            onValueChange = { otp = it },
            label = { Text("OTP") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (otp.length == 6) {
                    authViewModel.verifyOtp(
                        phoneNumber = phoneNumber,
                        otp = otp
                    )
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Gold),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(
                text = "Verify OTP",
                fontSize = 16.sp,
                color = CharcoalBlack,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (otpState) {
            is AuthViewModel.OtpState.Loading -> {
                CircularProgressIndicator(color = Gold)
            }

            is AuthViewModel.OtpState.Success -> {
                LaunchedEffect(Unit) {
                    onVerified()
                }
            }

            is AuthViewModel.OtpState.Error -> {
                Text(
                    text = otpState.message,
                    color = MaterialTheme.colorScheme.error
                )
            }

            else -> Unit
        }
    }
}
