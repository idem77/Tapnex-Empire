package com.tapnexempire.ui.wallet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.R
import com.tapnexempire.data.model.WithdrawHistoryModel
import com.tapnexempire.ui.theme.EmpireWhite
import com.tapnexempire.viewmodel.WithdrawHistoryViewModel

@Composable
fun MyWithdrawScreen(

    userId: String,

    onBack: () -> Unit,

    viewModel: WithdrawHistoryViewModel = hiltViewModel()

) {

    val withdraws by viewModel.withdraws.collectAsState()

    LaunchedEffect(userId) {

        viewModel.startListening(userId)

    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(

            painter = painterResource(R.drawable.wallet_bg),

            contentDescription = null,

            modifier = Modifier.Modifier.matchParentSize(),

            contentScale = ContentScale.Crop
        )

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)

        ) {

            Spacer(modifier = Modifier.height(30.dp))

            Text(

                text = "👑 My Rewards",

                style = MaterialTheme.typography.headlineLarge,

                color = EmpireWhite
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (withdraws.isEmpty()) {

                Box(
                    modifier = Modifier.fillMaxSize(),

                    contentAlignment = Alignment.Center
                ) {

                    CircularProgressIndicator()
                }

            } else {

                LazyColumn(

                    verticalArrangement = Arrangement.spacedBy(16.dp)

                ) {

                    items(withdraws) { withdraw ->

                        WithdrawRewardCard(

                            withdraw = withdraw

                        )

                    }

                    item {

                        Spacer(
                            modifier = Modifier.height(40.dp)
                        )

                    }

                }

            }

        }

    }

}
