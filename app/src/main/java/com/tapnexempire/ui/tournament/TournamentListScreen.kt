package com.tapnexempire.ui.tournament

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
    LaunchedEffect(Unit) { viewModel.loadTournaments() }

    if (tournaments.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No tournaments available 😌")
        }
    } else {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(tournaments) { tournament ->
                Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(tournament.name)
                        Text("Entry Fee: ${tournament.entryFee}")
                        Text("Players: ${tournament.joinedPlayers}/${tournament.maxPlayers}")
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { viewModel.joinTournament(userId, tournament.id) }) {
                            Text("Join")
                        }
                    }
                }
            }
        }
    }
}
