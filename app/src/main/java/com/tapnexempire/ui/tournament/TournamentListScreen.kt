package com.tapnexempire.ui.tournament

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapnexempire.viewmodel.TournamentViewModel

@Composable
fun TournamentListScreen(
    viewModel: TournamentViewModel,
    userId: String
) {

    val tournaments by viewModel.tournaments.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadTournaments()
    }

    if (tournaments.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No tournaments available 😌")
        }
    } else {
        LazyColumn {
            items(tournaments) { tournament ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(tournament.title)

                        Text("Entry: ${tournament.entryCoins}")

                        Text(
                            "Players: ${tournament.joinedPlayers}/${tournament.maxPlayers}"
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                // TODO: call joinTournament via ViewModel
                            }
                        ) {
                            Text("Join")
                        }
                    }
                }
            }
        }
    }
}
