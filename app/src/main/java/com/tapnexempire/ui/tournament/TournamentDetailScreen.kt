package com.tapnexempire.ui.tournament.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.viewmodel.TournamentViewModel

@Composable
fun TournamentDetailScreen(

    tournamentId: String,

    userId: String,

    entryFee:Long,
    
    viewModel: TournamentViewModel = hiltViewModel()
) {

    var loading by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Tournament ID"
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = tournamentId
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(

                enabled = !loading,

                onClick = {

                    loading = true

                    viewModel.joinTournament(

                        tournamentId = tournamentId,

                        userId = userId,

                        onResult = { success, message ->

                            loading = false

                            println(message)
                        }
                    )
                }

            ) {

                Text(

                    if (loading)
                        "Joining..."
                    else
                        "Join Tournament"
                )
            }
        }
    }
}
