package com.tapnexempire.ui.tournament

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapnexempire.viewmodel.TournamentViewModel
import com.tapnexempire.data.model.TournamentModel

@Composable
fun TournamentListScreen(
    viewModel: TournamentViewModel,
    userId: String
) {

    val tournaments by viewModel.tournaments.collectAsState()
    val joinState by viewModel.joinState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.startListening() // 🔥 real-time data
    }

    Column(modifier = Modifier.fillMaxSize()) {

        Text(
            text = "Tournaments 🎮",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        when {
            tournaments.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No tournaments available 😌")
                }
            }

            else -> {
                LazyColumn {
                    items(tournaments) { tournament ->
                        TournamentItem(
                            tournament = tournament,
                            onJoinClick = {
                                viewModel.joinTournament(
                                    tournamentId = tournament.id,
                                    userId = userId,
                                    entryFee = tournament.entryCoins
                                )
                            }
                        )
                    }
                }
            }
        }
    }

    // 🔥 JOIN RESULT UI (IMPORTANT)
    joinState?.let { result ->
        LaunchedEffect(result) {
            result.onSuccess {
                // success message
            }.onFailure {
                // error message
            }
        }
    }
}

@Composable
private fun TournamentItem(
    tournament: TournamentModel,
    onJoinClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = tournament.title,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text("Entry Fee: ${tournament.entryCoins} coins")

            Text(
                "Players: ${tournament.joinedPlayers}/${tournament.maxPlayers}"
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = onJoinClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Join Tournament 🚀")
            }
        }
    }
}
