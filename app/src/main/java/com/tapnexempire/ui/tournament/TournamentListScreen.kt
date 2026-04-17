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
            tournaments = list.map {
                TournamentModel(
                    id = it["id"] as? String ?: "",
                    name = it["name"] as? String ?: "Tournament",
                    entryFee = it["entryFee"] as? Long ?: 0,
                    maxPlayers = it["maxPlayers"] as? Long ?: 0,
                    joinedPlayers = it["joinedPlayers"] as? Long ?: 0,
                    createdAt = it["createdAt"] as? Long ?: 0
                )
            }
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
