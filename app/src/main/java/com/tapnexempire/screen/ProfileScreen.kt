package com.tapnexempire.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Profile", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))

        Text("Name: Queen 👑")
        Text("Email: queen@example.com")

        Spacer(Modifier.height(16.dp))
        Button(onClick = onBackClick) {
            Text("Back")
        }
    }
}
