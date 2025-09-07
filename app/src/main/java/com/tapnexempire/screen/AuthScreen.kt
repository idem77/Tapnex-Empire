package com.tapnexempire.screen

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(onContinue: () -> Unit = {}) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Auth") }) }
    ) { padding ->
        Button(
            onClick = onContinue,
            modifier = Modifier.padding(padding)
        ) {
            Text("Continue")
        }
    }
}
