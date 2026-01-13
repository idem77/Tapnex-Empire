package com.tapnexempire.ui.tournament

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
fun MyTournamentsScreen(
    viewModel: TournamentViewModel,
    onTournamentClick: (String) -> Unit
) {
    val tournaments by viewModel.tournaments.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "My Tournaments 🎮",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (tournaments.isEmpty()) {
            Text("You haven’t joined any tournament yet 😌")
        } else {
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
}
