package com.tapnexempire.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapnexempire.components.PrimaryButton

@Composable
fun RedeemScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Redeem Coins", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))

        PrimaryButton(text = "Redeem 50 Coins") {
            // Handle redeem
        }
    }
}
