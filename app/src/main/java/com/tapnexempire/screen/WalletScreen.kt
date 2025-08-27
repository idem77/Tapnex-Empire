package com.tapnexempire.screen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapnexempire.components.CoinCard
import com.tapnexempire.components.PrimaryButton

@Composable
fun WalletScreen(goToRedeem: () -> Unit) {
    var balance by remember { mutableStateOf(1000) }
    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        CoinCard(balance = balance)
        PrimaryButton("Redeem", onClick = goToRedeem)
    }
}
