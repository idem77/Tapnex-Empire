package com.tapnexempire.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapnexempire.viewmodel.WalletViewModel

@Composable
fun DailyRewardsScreen(
    walletViewModel: WalletViewModel
) {
    val wallet = walletViewModel.walletState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text("Daily Rewards", style = MaterialTheme.typography.headlineSmall)

        // Example: Daily login reward
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Login Reward: 500 Coins", style = MaterialTheme.typography.bodyLarge)
                Button(onClick = { walletViewModel.claimDailyReward(50) }) {
                    Text("Claim")
                }
            }
        }

        // You can add more daily reward cards here
    }
}
