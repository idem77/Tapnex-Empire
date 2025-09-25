package com.tapnexempire.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.components.AppButton
import com.tapnexempire.components.RewardCard
import com.tapnexempire.ui.theme.CharcoalBlack
import com.tapnexempire.ui.theme.Gold

@Composable
fun WalletScreen(
    depositBalance: Int,
    withdrawableBalance: Int,
    referralRewards: List<Pair<String, Int>>, // title and amount
    onDepositClick: () -> Unit,
    onWithdrawClick: () -> Unit,
    onTransactionHistoryClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Deposit Balance (non-withdrawable)
        Text(text = "Deposit Balance", fontSize = 16.sp, color = CharcoalBlack)
        RewardCard(
            rewardTitle = "Deposit Balance",
            rewardAmount = depositBalance,
            onClaim = {} // non-clickable
        )

        // Withdrawable Balance
        Text(text = "Withdrawable Balance", fontSize = 16.sp, color = CharcoalBlack)
        RewardCard(
            rewardTitle = "Withdrawable Balance",
            rewardAmount = withdrawableBalance,
            onClaim = {} // non-clickable
        )

        // Quick Actions
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AppButton(
                text = "Deposit",
                modifier = Modifier.weight(1f),
                onClick = onDepositClick
            )
            AppButton(
                text = "Withdraw",
                modifier = Modifier.weight(1f),
                onClick = onWithdrawClick
            )
        }

        // Referral / Task Rewards List
        Text(text = "Referral / Task Rewards", fontSize = 16.sp, color = CharcoalBlack)
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(referralRewards) { reward ->
                RewardCard(
                    rewardTitle = reward.first,
                    rewardAmount = reward.second,
                    onClaim = {} // non-clickable
                )
            }
        }

        // Transaction History Button
        AppButton(
            text = "Transaction History",
            onClick = onTransactionHistoryClick
        )
    }
}
