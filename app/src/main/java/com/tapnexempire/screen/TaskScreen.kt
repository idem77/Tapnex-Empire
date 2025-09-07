package com.tapnexempire.screen

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(onTaskClick: () -> Unit = {}) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Tasks") }) }
    ) { padding ->
        Text("Task screen content", modifier = Modifier.padding(padding))
    }
}
