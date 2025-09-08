package com.tapnexempire.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tapnexempire.components.TopBar

@Composable
fun RedeemScreen(onBackClick: () -> Unit = {}) {
    Scaffold(
        topBar = { TopBar(title = "Redeem", onBackClick = onBackClick) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text("Redeem Rewards coming soon", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
