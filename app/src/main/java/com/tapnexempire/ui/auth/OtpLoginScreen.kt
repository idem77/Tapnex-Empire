package com.tapnexempire.ui.auth

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.viewmodel.AuthViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpLoginScreen(
    viewModel: AuthViewModel,
    onOtpSent: () -> Unit
) {
    val context = LocalContext.current as Activity

    var phoneNumber by remember { mutableStateOf("") }
    val isLoading by viewModel.isLoading.collectAsState()
    val otpSent by viewModel.otpSent.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(otpSent) {
        if (otpSent) onOtpSent()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login with Phone", fontSize = 26.sp, color = MaterialTheme.colorScheme.primary)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Enter Phone Number") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (phoneNumber.isNotEmpty()) {
                    viewModel.sendOtp("+91$phoneNumber", context)
                }
            },
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(20.dp), color = MaterialTheme.colorScheme.onPrimary)
            } else {
                Text("Send OTP")
            }
        }

        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = errorMessage ?: "",
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}
