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

    var tournaments by remember {
        mutableStateOf<List<TournamentModel>>(emptyList())
    }

    LaunchedEffect(Unit) {
        viewModel.listenTournaments { list ->
            // ✅ DIRECT USE (NO MAP)
            tournaments = list
        }
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {

        items(tournaments) { tournament ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    Text(text = tournament.name)
                    Text(text = "Entry: ${tournament.entryFee} coins")
                    Text(text = "Players: ${tournament.joinedPlayers}/${tournament.maxPlayers}")

                    Button(onClick = {
                        viewModel.joinTournament(
                            tournamentId = tournament.id,
                            userId = userId,
                            entryFee = tournament.entryFee
                        ) { _, _ -> }
                    }) {
                        Text("Join")
                    }
                }
            }
        }
    }
}
