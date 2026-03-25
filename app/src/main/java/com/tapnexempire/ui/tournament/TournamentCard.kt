package com.tapnexempire.ui.tournament

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapnexempire.data.model.TournamentModel

@Composable
fun TournamentCard(
    tournament: TournamentModel,
    onClick: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(tournament.name, style = MaterialTheme.typography.titleMedium)
            Text("Entry Fee: ${tournament.entryFee}")
            Text("Players: ${tournament.joinedPlayers}/${tournament.maxPlayers}")
            Button(onClick = onClick, modifier = Modifier.fillMaxWidth()) { Text("View / Join") }
        }
    }
}
