package com.tapnexempire.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HelpScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Help & FAQ", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "1. How to earn coins?", style = MaterialTheme.typography.body1)
        Text(text = "2. How to redeem rewards?", style = MaterialTheme.typography.body1)
    }
}
