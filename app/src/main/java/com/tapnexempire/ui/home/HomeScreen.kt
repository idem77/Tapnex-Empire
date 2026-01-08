package com.tapnexempire.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tapnexempire.components.CoinCard
import com.tapnexempire.components.GameTile
import com.tapnexempire.components.RewardCard
import com.tapnexempire.viewmodel.TaskViewModel
import com.tapnexempire.viewmodel.WalletViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    walletViewModel: WalletViewModel = hiltViewModel(),
    taskViewModel: TaskViewModel = hiltViewModel()
) {

    val walletState by walletViewModel.walletState.collectAsState()
    val dailyReward by taskViewModel.dailyReward.collectAsState()

    LaunchedEffect(Unit) {
        walletViewModel.fetchWallet()
        taskViewModel.fetchDailyReward()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            CoinCard(
                coins = walletState.coins,
                onWalletClick = {
                    navController.navigate("wallet")
                }
            )
        }

        item {
            Text(
                text = "Games",
                style = MaterialTheme.typography.titleMedium
            )
        }

        item {
            GameTile(
                gameName = "Ludo",
                onClick = {
                    navController.navigate("ludo")
                }
            )
        }

        item {
            Text(
                text = "Rewards",
                style = MaterialTheme.typography.titleMedium
            )
        }

        item {
            RewardCard(
                title = "Daily Reward",
                coins = dailyReward.coins,
                onClick = {
                    taskViewModel.claimDailyReward()
                }
            )
        }
    }
}
