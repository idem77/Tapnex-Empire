package com.tapnexempire.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RedeemScreen(onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Redeem Rewards", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        Text("Amazon Gift Card - 100 coins")
        Text("Google Play - 50 coins")
        Text("Flipkart Gift Card - 200 coins")

        Spacer(Modifier.height(16.dp))
        Button(onClick = onBackClick) {
            Text("Back")
        }
    }
}
