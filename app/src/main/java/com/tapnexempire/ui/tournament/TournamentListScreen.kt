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

    var tournaments by remember { mutableStateOf<List<Map<String, Any>>>(emptyList()) }

    LaunchedEffect(Unit) {
        viewModel.repo.listenToTournaments {
            tournaments = it
        }
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {

        items(tournaments) { item ->

            val id = item["id"] as? String ?: ""
            val name = item["name"] as? String ?: "Tournament"
            val entryFee = item["entryFee"] as? Long ?: 0L

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    Text(text = name)
                    Text(text = "Entry: $entryFee coins")

                    Button(onClick = {
                        viewModel.joinTournament(
                            tournamentId = id,
                            userId = userId,
                            entryFee = entryFee
                        ) { _, _ -> }
                    }) {
                        Text("Join")
                    }
                }
            }
        }
    }
}
