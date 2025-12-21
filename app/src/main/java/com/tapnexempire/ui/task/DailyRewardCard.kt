package com.tapnexempire.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DailyRewardCard(
    rewardCoins: Int,
    claimed: Boolean
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Daily Login Reward",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Get $rewardCoins coins for today")

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { },
                enabled = !claimed,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (claimed) "Already Claimed" else "Claim Reward")
            }
        }
    }
}
