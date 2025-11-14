package com.tapnexempire.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.unit.dp
import com.tapnexempire.viewmodel.AuthViewModel

@Composable
fun OtpLoginScreen(
    viewModel: AuthViewModel,
    onOtpSent: () -> Unit
) {
    var phone by remember { mutableStateOf("") }
    val isLoading by viewModel.isLoading.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Enter your phone number", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = phone,
            onValueChange = {
                if (it.length <= 10) phone = it.filter { ch -> ch.isDigit() }
            },
            label = { Text("Phone Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (phone.length == 10) {
                    viewModel.sendOtp(phone.trim())
                    onOtpSent()
                }
            },
            enabled = phone.length == 10 && !isLoading
        ) {
            Text(if (isLoading) "Sending..." else "Send OTP")
        }
    }
}
