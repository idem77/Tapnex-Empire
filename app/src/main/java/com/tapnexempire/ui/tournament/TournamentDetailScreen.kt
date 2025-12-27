package com.tapnexempire.ui.tournament.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.ui.theme.CharcoalBlack
import com.tapnexempire.ui.theme.Gold
import com.tapnexempire.ui.theme.LightCream

@Composable
fun TournamentDetailScreen(
    tournamentId: String,
    viewModel: TournamentDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(tournamentId) {
        viewModel.loadTournament(tournamentId)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightCream)
            .padding(16.dp)
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Tournament Details",
                        fontSize = 24.sp,
                        color = CharcoalBlack
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Your Coins: ${state.coins}",
                        fontSize = 16.sp,
                        color = CharcoalBlack
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Entry Fee: 250 coins",
                        fontSize = 16.sp,
                        color = CharcoalBlack
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    if (state.isJoined) {
                        Text(
                            text = "✅ You have joined this tournament",
                            color = CharcoalBlack,
                            fontSize = 16.sp
                        )
                    } else {
                        Button(
                            onClick = { viewModel.joinTournament(tournamentId) },
                            colors = ButtonDefaults.buttonColors(containerColor = Gold),
                            modifier = Modifier.fillMaxWidth(0.6f)
                        ) {
                            Text("Join Tournament", color = CharcoalBlack)
                        }
                    }

                    state.error?.let {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = it,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
}
