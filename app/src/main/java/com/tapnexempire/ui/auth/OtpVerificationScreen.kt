package com.tapnexempire.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.tapnexempire.ui.theme.*

@Composable
fun OtpVerificationScreen(
    phoneNumber: String,
    onVerified: () -> Unit
) {
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
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onVerified() },
            colors = ButtonDefaults.buttonColors(containerColor = Gold),
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text(
                text = "Verify OTP",
                fontSize = 16.sp,
                color = CharcoalBlack,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
