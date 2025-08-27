package com.tapnexempire.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HelpScreen() {
    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("FAQ")
        Text("• How to earn coins? Complete tasks & play games.")
        Text("• How to redeem? Open Wallet → Redeem.")
        Text("• Need help? Contact support in Phase-2.")
    }
}
