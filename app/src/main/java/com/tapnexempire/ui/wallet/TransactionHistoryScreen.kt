package com.tapnexempire.ui.wallet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.R
import com.tapnexempire.ui.theme.EmpireGold
import com.tapnexempire.ui.theme.EmpireWhite
import com.tapnexempire.viewmodel.TransactionViewModel

@Composable
fun TransactionHistoryScreen(

    userId: String,

    viewModel: TransactionViewModel =
        hiltViewModel()
) {

    val transactions by
        viewModel.transactions.collectAsState()

    LaunchedEffect(userId) {

        viewModel.startTransactionListener(userId)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // 👑 BACKGROUND
        Image(
            painter =
                painterResource(id = R.drawable.wallet_bg),

            contentDescription = null,

            modifier = Modifier.fillMaxSize(),

            contentScale = ContentScale.FillBounds
        )

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp)
                .padding(top = 50.dp)
        ) {

            // 👑 TITLE
            Text(

                text = "📜 Transactions",

                style =
                    MaterialTheme.typography.headlineLarge,

                color = EmpireWhite
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(

                text =
                    "Track Your Empire Activity",

                color = EmpireGold,

                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (transactions.isEmpty()) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Text(

                        text = "No Transactions Yet",

                        color = EmpireWhite
                    )
                }

            } else {

                LazyColumn(

                    verticalArrangement =
                        Arrangement.spacedBy(14.dp),

                    contentPadding =
                        PaddingValues(bottom = 100.dp)
                ) {

                    items(transactions) { transaction ->

                        Card(

                            modifier =
                                Modifier.fillMaxWidth(),

                            colors =
                                CardDefaults.cardColors(

                                    containerColor =
                                        MaterialTheme
                                            .colorScheme
                                            .surface
                                ),

                            elevation =
                                CardDefaults.cardElevation(
                                    defaultElevation = 8.dp
                                )
                        ) {

                            Column(

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(18.dp)
                            ) {

                                Text(

                                    text = transaction.type,

                                    color = EmpireGold,

                                    fontSize = 20.sp
                                )

                                Spacer(
                                    modifier =
                                        Modifier.height(10.dp)
                                )

                                Text(

                                    text =
                                        "💰 Amount: ₹${transaction.amount}",

                                    color = EmpireWhite
                                )

                                Spacer(
                                    modifier =
                                        Modifier.height(6.dp)
                                )

                                Text(

                                    text =
                                        "📝 ${transaction.description}",

                                    color = EmpireWhite
                                )

                                Spacer(
                                    modifier =
                                        Modifier.height(6.dp)
                                )

                                Text(

                                    text =
                                        "👤 ${transaction.userId}",

                                    color = EmpireWhite.copy(
                                        alpha = 0.7f
                                    ),

                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
