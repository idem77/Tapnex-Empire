package com.tapnexempire.ui.tournament

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tapnexempire.models.TournamentModel
import com.tapnexempire.service.TournamentService

@Composable
fun MyTournamentsScreen() {
    val tournaments = TournamentService.getTournaments().filter { it.isJoined }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "My Tournaments",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (tournaments.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("You haven't joined any tournaments yet.")
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(tournaments) { tournament ->
                    TournamentCard(tournament)
                }
            }
        }
    }
}

@Composable
fun TournamentCard(tournament: TournamentModel) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(tournament.title, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Entry Fee: ${tournament.entryFee} coins")
            Text("Players: ${tournament.joinedPlayers}/${tournament.totalPlayers}")
            Text("Prize Pool: ${tournament.prizePool} coins")
        }
    }
}
