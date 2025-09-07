package com.tapnexempire.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AuthScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Login / Signup", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        Button(onClick = { /* handle login */ }) {
            Text("Login")
        }
    }
}
