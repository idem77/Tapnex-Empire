package com.tapnexempire.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HelpScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Help", style = MaterialTheme.typography.titleLarge) }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("FAQ:", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            Text("1. How to earn coins?\nComplete tasks and play games.")
            Spacer(Modifier.height(8.dp))
            Text("2. How to redeem?\nGo to Redeem tab and select rewards.")
        }
    }
}
