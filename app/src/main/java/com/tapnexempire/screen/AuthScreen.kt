package com.tapnexempire.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapnexempire.repository.AuthRepository
import kotlinx.coroutines.launch

@Composable
fun AuthScreen() {
    val repo = AuthRepository()
    val scope = rememberCoroutineScope()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            scope.launch {
                val result = repo.registerUser(email, password)
                message = result.fold(
                    onSuccess = { "Registered: $it" },
                    onFailure = { it.message ?: "Error" }
                )
            }
        }) {
            Text("Register")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            scope.launch {
                val result = repo.loginUser(email, password)
                message = result.fold(
                    onSuccess = { "Logged in: $it" },
                    onFailure = { it.message ?: "Error" }
                )
            }
        }) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = message)
    }
}
