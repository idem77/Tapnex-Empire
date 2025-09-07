package com.tapnexempire.screen

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(onPlayClick: () -> Unit = {}) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Game") }) }
    ) { padding ->
        Text("Game screen content", modifier = Modifier.padding(padding))
    }
}
