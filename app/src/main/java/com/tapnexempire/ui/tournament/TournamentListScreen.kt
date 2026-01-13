package com.tapnexempire.ui.tournament

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapnexempire.models.TournamentModel
import com.tapnexempire.viewmodel.TournamentViewModel

@Composable
fun TournamentListScreen(
    viewModel: TournamentViewModel,
    onTournamentClick: (String) -> Unit
) {
    val tournaments by viewModel.tournaments.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadTournaments()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Tournaments 🏆",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tournaments) { tournament ->
                TournamentCard(
                    tournament = tournament,
                    onClick = { onTournamentClick(tournament.id) }
                )
            }
        }
    }
}

@Composable
private fun TournamentCard(
    tournament: TournamentModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = tournament.title,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text("Entry: ${tournament.entryCoins} coins")
            Text("Players: ${tournament.joinedPlayers}/${tournament.maxPlayers}")
            Text("Status: ${tournament.status}")
        }
    }
}
