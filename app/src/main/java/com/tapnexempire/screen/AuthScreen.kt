package com.tapnexempire.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapnexempire.ui.theme.RoyalTeal
import com.tapnexempire.ui.theme.White

@Composable
fun AuthScreen(onContinue: () -> Unit = {}) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = RoyalTeal
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Welcome to Tapnex Empire 👑",
                style = MaterialTheme.typography.headlineSmall,
                color = White
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { onContinue() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Continue")
            }
        }
    }
}
