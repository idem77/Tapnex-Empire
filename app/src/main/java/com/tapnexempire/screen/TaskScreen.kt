package com.tapnexempire.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Daily Tasks", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "1. Play 3 games", style = MaterialTheme.typography.body1)
        Text(text = "2. Share the app", style = MaterialTheme.typography.body1)
    }
}
