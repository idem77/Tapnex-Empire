package com.tapnexempire.ui.wallet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tapnexempire.R
import com.tapnexempire.viewmodel.WalletViewModel

@Composable
fun WalletScreen(
    viewModel: WalletViewModel,
    userId: String
) {

    val wallet by viewModel.walletState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.startListening(userId)
    }

    if (wallet == null) {
        CircularProgressIndicator()
    } else {
        Column {
            Text("Total: ${wallet!!.totalCoins}")
            Text("Bonus: ${wallet!!.bonusCoins}")
            Text("Withdrawable: ${wallet!!.withdrawableCoins}")
        }
    }
}
