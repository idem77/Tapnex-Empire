package com.tapnexempire.ui.tournament

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.ui.theme.LightCream
import com.tapnexempire.ui.theme.CharcoalBlack
import com.tapnexempire.ui.theme.Gold
import com.tapnexempire.viewmodel.TournamentViewModel

@Composable
fun TournamentDetailScreen(
    tournamentId: Int,
    onJoinClick: () -> Unit
) {
    val tournamentViewModel: TournamentViewModel = hiltViewModel()
    val tournament = tournamentViewModel.getTournamentById(tournamentId).collectAsState(initial = null).value

    tournament?.let { t ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightCream)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = t.name,
                fontSize = 24.sp,
                color = CharcoalBlack,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Prize: ${t.prize}",
                fontSize = 18.sp,
                color = CharcoalBlack.copy(alpha = 0.8f),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Entry Fee: ${t.entryFee} coins",
                fontSize = 16.sp,
                color = CharcoalBlack.copy(alpha = 0.7f),
                modifier = Modifier.padding(bottom = 32.dp)
            )

            Button(
                onClick = { onJoinClick() },
                colors = ButtonDefaults.buttonColors(containerColor = Gold),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(50.dp)
            ) {
                Text(
                    text = "Join Tournament",
                    color = CharcoalBlack,
                    fontSize = 16.sp
                )
            }
        }
    } ?: run {
        // Loading / Not found
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Loading...", color = CharcoalBlack)
        }
    }
}
