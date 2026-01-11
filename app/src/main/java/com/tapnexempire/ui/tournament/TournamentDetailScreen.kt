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

    when {
        state.isLoading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        state.error != null -> {
            Text(
                text = state.error ?: "",
                color = MaterialTheme.colorScheme.error
            )
        }

        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(text = state.name, style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(8.dp))
                Text(text = "Prize: ${state.prize}")
                Text(text = "Entry Fee: ${state.entryFee}")
                Spacer(Modifier.height(24.dp))

                if (state.isJoined) {
                    Text("✅ Already Joined")
                } else {
                    Button(
                        onClick = { viewModel.joinTournament(tournamentId) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Join Tournament")
                    }
                }
            }
        }
    }
}
