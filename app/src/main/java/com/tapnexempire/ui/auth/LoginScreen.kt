package com.tapnexempire.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tapnexempire.navigation.Screen

@Composable
fun LoginScreen(navController: NavController) {
    var phoneNumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                // Navigate to OTP Verification screen
                navController.navigate(Screen.OtpVerification.route)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Send OTP")
        }

        Spacer(modifier = Modifier.height(10.dp))

        TextButton(
            onClick = {
                // Navigate to Signup screen
                navController.navigate(Screen.Signup.route)
            }
        ) {
            Text("Don’t have an account? Sign Up")
        }
    }
}
