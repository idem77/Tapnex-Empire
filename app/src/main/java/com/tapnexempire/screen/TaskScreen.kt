package com.tapnexempire.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tasks", style = MaterialTheme.typography.titleLarge) }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Complete tasks to earn coins!", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
