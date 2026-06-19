package com.tapnexempire.ui.tournament

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.data.model.MyTournamentModel
import com.tapnexempire.viewmodel.MyTournamentViewModel

@Composable
fun MyTournamentScreen(

    userId: String,

    vm: MyTournamentViewModel =
        hiltViewModel()
) {

    var tournaments by remember {

        mutableStateOf<List<MyTournamentModel>>(
            emptyList()
        )
    }

    LaunchedEffect(Unit) {

        vm.getMyTournaments(userId) {

            tournaments = it
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {

        Text(
            text = "🏆 My Tournaments",
            style =
                MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        LazyColumn {

            items(tournaments) { tournament ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                ) {

                    Column(
                        modifier =
                            Modifier.padding(12.dp)
                    ) {

                        Text(
                            "🏆 ${tournament.title}"
                        )

                        Text(
                            "📊 Status: ${tournament.status}"
                        )

                        Text(
                            "🎯 Score: ${tournament.score}"
                        )

                        Text(
                            "🥇 Rank: ${tournament.rank}"
                        )
                    }
                }
            }
        }
    }
}
