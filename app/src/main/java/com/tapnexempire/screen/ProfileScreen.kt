package com.tapnexempire.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Profile", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Username: Gamer123", style = MaterialTheme.typography.body1)
        Text(text = "Email: gamer123@example.com", style = MaterialTheme.typography.body1)
    }
}
