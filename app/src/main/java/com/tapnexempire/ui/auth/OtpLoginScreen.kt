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
fun OtpLoginScreen(
    onOtpSent: (verificationId: String) -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    var phone by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current
    val otpState by viewModel.otpState.collectAsState()

    // 🔁 Navigate when OTP sent
    LaunchedEffect(otpState) {
        otpState?.let { verificationId ->
            onOtpSent(verificationId)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Login with Phone",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = {
                if (it.length <= 10) phone = it
            },
            label = { Text("Phone Number") },
            placeholder = { Text("10 digit mobile number") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        error?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(Modifier.height(24.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                error = null

                if (phone.length != 10) {
                    error = "Enter valid 10 digit number"
                    return@Button
                }

                val formattedPhone = "+91$phone"

                viewModel.sendOtp(
                    activity = context as Activity,
                    phone = formattedPhone,
                    onError = { msg ->
                        error = msg
                    }
                )
            }
        ) {
            Text("Send OTP")
        }
    }
}
