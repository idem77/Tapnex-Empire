package com.tapnexempire.ui.tournament

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tapnexempire.R
import com.tapnexempire.data.model.TournamentModel
import com.tapnexempire.viewmodel.TournamentViewModel

@Composable
fun TournamentListScreen(
    viewModel: TournamentViewModel,
    userId: String
) {

    val tournaments by viewModel.tournaments.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.startListening()
    }

    LazyColumn {
        items(tournaments) { tournament ->

            Card(
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    Text(tournament.title)
                    Text("Entry: ${tournament.entrycoins}")
                    Text("Players: ${tournament.joinedPlayers}/${tournament.maxPlayers}")

                    Button(
                        onClick = {
                            // call join from repo (via VM later)
                        }
                    ) {
                        Text("Join")
                    }
                }
            }
        }
    }
}
