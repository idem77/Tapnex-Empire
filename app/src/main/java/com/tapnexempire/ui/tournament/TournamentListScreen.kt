package com.tapnexempire.ui.tournament

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tapnexempire.navigation.Routes
import com.tapnexempire.viewmodel.TournamentViewModel
import com.tapnexempire.data.model.TournamentModel

@Composable
fun TournamentListScreen(

    navController: NavController,

    viewModel: TournamentViewModel,

    userId: String
) {

    var tournaments by remember {
        mutableStateOf<List<TournamentModel>>(emptyList())
    }

    LaunchedEffect(Unit) {

        viewModel.listenTournaments { list ->

            tournaments = list
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        items(tournaments) { tournament ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = tournament.title,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Entry Fee: ${tournament.entryFee} coins"
                    )

                    Text(
                        text = "Players: ${tournament.joinedPlayers}/${tournament.maxPlayers}"
                    )

                    Text(
                        text = "Prize Pool: ${tournament.prizePool} coins"
                    )

                    Text(
                        text = "Status: ${tournament.status}"
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(

                        onClick = {

                            navController.navigate(
                                "${Routes.TOURNAMENT_DETAIL}/${tournament.id}"
                            )
                        }

                    ) {

                        Text("View Tournament")
                    }
                }
            }
        }
    }
}
