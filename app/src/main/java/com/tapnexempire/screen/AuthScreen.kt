package com.tapnexempire.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier   // ✅ Fix added
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
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Welcome to Tapnex Empire",
                style = MaterialTheme.typography.headlineMedium,
                color = White
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onContinue() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Continue")
            }
        }
    }
}
