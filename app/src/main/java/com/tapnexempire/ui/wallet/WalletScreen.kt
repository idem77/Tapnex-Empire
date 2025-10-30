package com.tapnexempire.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.viewmodel.WalletViewModel

@Composable
fun WalletScreen(
    viewModel: WalletViewModel = hiltViewModel(),
    onDepositClick: () -> Unit,
    onWithdrawClick: () -> Unit,
    onTransactionHistoryClick: () -> Unit
) {
    val depositBalance = viewModel.depositBalance.collectAsState().value
    val withdrawableBalance = viewModel.withdrawableBalance.collectAsState().value
    val referralRewards = viewModel.referralRewards.collectAsState().value
    val totalCoins = viewModel.totalCoins.collectAsState().value

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("My Wallet 💰", fontWeight = FontWeight.Bold) }
            )
        }
    ) { innerPadding ->
