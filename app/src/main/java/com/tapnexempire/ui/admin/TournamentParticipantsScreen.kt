package com.tapnexempire.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tapnexempire.viewmodel.AdminViewModel

@Composable
fun TournamentParticipantsScreen(
    tournamentId: String,
    vm: AdminViewModel = viewModel()
) {

    var participants by remember {
        mutableStateOf<List<Map<String, Any>>>(emptyList())
    }

    LaunchedEffect(Unit) {

    vm.listenParticipants(tournamentId) {

        participants =
            it.sortedByDescending { player ->

                (player["score"] as? Long) ?: 0L
            }
    }
    }


    Column(
        modifier = Modifier.fillMaxSize()
            .padding(12.dp)
    ) {

        Text(
            "🏆 Tournament Players",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(16.dp))

        LazyColumn {

            items(participants) { player ->

                val userId =
                    player["userId"]?.toString() ?: ""

                val score =
                    (player["score"] as? Long) ?: 0L

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                ) {

                    Column(
                        Modifier.padding(12.dp)
                    ) {

                        Text("👤 $userId")
                        Text("🏆 Score: $score")

                        Spacer(
                            Modifier.height(8.dp)
                        )

                        Row {

                            Button(
                                onClick = {
                                    vm.updateScore(
                                        tournamentId,
                                        userId,
                                        score + 10
                                    )
                                }
                            ) {
                                Text("+10")
                            }

                                Button(
    onClick = {

        vm.setRank(
            tournamentId,
            userId,
            1
        )

        vm.rewardUser(
            tournamentId,
            userId,
            500
        )
    }
) {
    Text("🏆 Winner")
                                }
                          
                            Spacer(
                                Modifier.width(8.dp)
                            )

                            Button(
                                onClick = {
                                    vm.updateScore(
                                        tournamentId,
                                        userId,
                                        score + 50
                                    )
                                }
                            ) {
                                Text("+50")
                            }
                        }
                    }
                }
            }
        }
    }
}
