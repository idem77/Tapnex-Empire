package com.tapnexempire.ui.tournament

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.components.AppButton
import com.tapnexempire.ui.theme.CharcoalBlack
import com.tapnexempire.ui.theme.Gold

@Composable
fun TournamentDetailScreen(
    tournament: Tournament,
    onJoinClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = tournament.name,
            fontSize = 24.sp,
            color = CharcoalBlack
        )

        Text(
            text = "Reward: ${tournament.reward} Coins",
            fontSize = 20.sp,
            color = Gold
        )

        Text(
            text = "Participants: ${tournament.participants}",
            fontSize = 16.sp,
            color = CharcoalBlack
        )

        // Join Button
        AppButton(
            text = "Join Tournament",
            onClick = onJoinClick
        )
    }
}
