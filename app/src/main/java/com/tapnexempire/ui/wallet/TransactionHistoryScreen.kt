package com.tapnexempire.ui.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.ui.theme.LightCream
import com.tapnexempire.ui.theme.CharcoalBlack
import com.tapnexempire.ui.theme.Gold
import com.tapnexempire.viewmodel.WalletViewModel

@Composable
fun TransactionHistoryScreen(
    viewModel: WalletViewModel = hiltViewModel()
) {
    val walletState by viewModel.walletState.collectAsState()
    val transactions = walletState.transactions

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightCream)
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Transaction History",
                fontSize = 24.sp,
                color = CharcoalBlack,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            if (transactions.isEmpty()) {
                Text(
                    text = "No transactions yet",
                    color = CharcoalBlack,
                    fontSize = 16.sp
                )
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(transactions) { tx ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = Gold.copy(alpha = 0.15f)
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = tx.type,
                                        color = CharcoalBlack,
                                        fontSize = 16.sp
                                    )
                                    Text(
                                        text = tx.date,
                                        color = CharcoalBlack.copy(alpha = 0.7f),
                                        fontSize = 12.sp
                                    )
                                }
                                Text(
                                    text = "${tx.amount} coins",
                                    color = CharcoalBlack,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
