package com.tapnexempire.ui.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.ui.theme.*
import com.tapnexempire.viewmodel.AuthViewModel

@Composable
fun OtpLoginScreen(
    onOtpSent: (String) -> Unit
) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val context = LocalContext.current
    var phoneNumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightCream)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Enter your Phone Number",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = CharcoalBlack
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone Number") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (phoneNumber.length == 10) {
                        authViewModel.sendOtp(phoneNumber)
                        onOtpSent(phoneNumber)
                    } else {
                        Toast.makeText(context, "Enter valid 10-digit number", Toast.LENGTH_SHORT).show()
                    }
                }
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (phoneNumber.length == 10) {
                    authViewModel.sendOtp(phoneNumber)
                    onOtpSent(phoneNumber)
                } else {
                    Toast.makeText(context, "Enter valid 10-digit number", Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Gold),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(
                text = "Send OTP",
                fontSize = 16.sp,
                color = CharcoalBlack,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
