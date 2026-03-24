package com.tapnexempire.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import com.tapnexempire.viewmodel.WalletViewModel

@Composable
fun WalletScreen(
    viewModel: WalletViewModel,
    userId: String
) {

    val wallet by viewModel.walletState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadWallet(userId)
    }

    if (wallet == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {

        val total =
            wallet!!.depositCoins +
            wallet!!.bonusCoins +
            wallet!!.withdrawableCoins

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text("Total Coins: $total")
            Text("Deposit: ${wallet!!.depositCoins}")
            Text("Bonus: ${wallet!!.bonusCoins}")
            Text("Withdrawable: ${wallet!!.withdrawableCoins}")
        }
    }
}
