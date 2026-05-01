package com.tapnexempire.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    goToSignup: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val state by viewModel.authState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 📧 Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        // 🔐 Password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(), // 🔥 hidden password
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 🔥 Login Button
        Button(
            onClick = {
                viewModel.login(email.trim(), password.trim())
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(10.dp))

        // 🔁 Signup Button
        TextButton(
            onClick = { goToSignup() }
        ) {
            Text("Don't have an account? Signup")
        }

        Spacer(modifier = Modifier.height(10.dp))

        // 🔄 State Handling
        when (state) {

            is AuthState.Loading -> {
                CircularProgressIndicator()
            }

            is AuthState.Success -> {
                LaunchedEffect(Unit) {
                    onLoginSuccess()
                }
            }

            is AuthState.Error -> {
                Text(
                    text = (state as AuthState.Error).message,
                    color = MaterialTheme.colorScheme.error
                )
            }

            else -> {}
        }
    }
}
