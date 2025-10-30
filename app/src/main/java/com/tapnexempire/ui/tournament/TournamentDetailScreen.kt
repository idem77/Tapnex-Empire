package com.tapnexempire.ui.tournament

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tapnexempire.models.TournamentModel
import com.tapnexempire.service.TournamentService

@Composable
fun TournamentDetailScreen(tournamentId: String, onJoin: () -> Unit) {
    val tournament = TournamentService.getTournamentById(tournamentId)

    if (tournament == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
            Text("Tournament not found.")
        }
        return
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        Text(
            text = tournament.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text("Entry Fee: ${tournament.entryFee} coins")
        Text("Prize Pool: ${tournament.prizePool} coins")
        Text("Players: ${tournament.joinedPlayers}/${tournament.totalPlayers}")
        Text("Duration: ${tournament.durationHours} hours")

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                TournamentService.joinTournament(tournament.id)
                onJoin()
            },
            enabled = !tournament.isJoined && !tournament.isFull
        ) {
            Text(if (tournament.isJoined) "Joined" else "Join Now")
        }
    }
}
