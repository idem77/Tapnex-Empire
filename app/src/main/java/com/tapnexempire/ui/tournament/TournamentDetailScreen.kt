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
                    modifier = Modifier.align(Alignment.Center),
                    color = Gold
                )
            }

            else -> {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Tournament Details 🏆",
                        fontSize = 24.sp,
                        color = CharcoalBlack
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    InfoRow(label = "Your Coins", value = "${state.coins}")
                    InfoRow(label = "Entry Fee", value = "250 coins")
                    InfoRow(label = "Type", value = "Paid Tournament")

                    Spacer(modifier = Modifier.height(32.dp))

                    if (state.isJoined) {
                        Text(
                            text = "✅ You have joined this tournament",
                            fontSize = 16.sp,
                            color = CharcoalBlack
                        )
                    } else {
                        Button(
                            onClick = { viewModel.joinTournament(tournamentId) },
                            colors = ButtonDefaults.buttonColors(containerColor = Gold),
                            modifier = Modifier.fillMaxWidth(0.7f)
                        ) {
                            Text(
                                text = "Join Tournament",
                                color = CharcoalBlack,
                                fontSize = 16.sp
                            )
                        }
                    }

                    state.error?.let { error ->
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = error,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun InfoRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = CharcoalBlack)
        Text(text = value, color = CharcoalBlack)
    }
}
