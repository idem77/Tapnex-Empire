package com.tapnexempire.ui.tournament

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tapnexempire.models.TournamentModel
import com.tapnexempire.service.TournamentService

@Composable
fun TournamentListScreen(onTournamentClick: (String) -> Unit) {
    val tournaments = TournamentService.getTournaments()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        Text(
            text = "Available Tournaments",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(tournaments) { tournament ->
                TournamentListItem(tournament = tournament, onClick = {
                    onTournamentClick(tournament.id)
                })
            }
        }
    }
}

@Composable
fun TournamentListItem(tournament: TournamentModel, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(tournament.title, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Entry Fee: ${tournament.entryFee} coins")
            Text("Prize Pool: ${tournament.prizePool} coins")
            Text("Players: ${tournament.joinedPlayers}/${tournament.totalPlayers}")
        }
    }
}
