package com.tapnexempire.ui.tournament

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tapnexempire.viewmodel.TournamentViewModel
import com.tapnexempire.model.TournamentModel

@Composable
fun TournamentDetailScreen(
    tournamentId: String,
    onBackClick: () -> Unit,
    viewModel: TournamentViewModel = viewModel()
) {
    val tournament = viewModel.getTournamentById(tournamentId)
    var joined by remember { mutableStateOf(tournament?.isJoined ?: false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Tournament Details", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF101820),
                    titleContentColor = Color.White
                )
            )
        },
        containerColor = Color(0xFFF8F9FA)
    ) { padding ->
        tournament?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(20.dp)
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(it.title, fontSize = 22.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(10.dp))

                Text("Entry Fee: ${it.entryFee} coins", color = Color.Gray)
                Text("Total Players: ${it.totalPlayers}", color = Color.Gray)
                Text("Reward: ${it.rewardCoins} coins", color = Color(0xFF007BFF), fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(30.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF0F0F0))
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (it.isFull) "Tournament Full"
                        else if (joined) "You’ve Joined!"
                        else "Join Now",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        if (!joined && !it.isFull) {
                            viewModel.joinTournament(it.id)
                            joined = true
                        }
                    },
                    enabled = !joined && !it.isFull,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (joined) Color.Gray else Color(0xFF007BFF)
                    )
                ) {
                    Text(
                        text = if (joined) "Joined" else "Join Tournament",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (joined) {
                    Text(
                        "You are successfully registered!",
                        color = Color(0xFF28A745),
                        fontSize = 14.sp
                    )
                }
            }
        } ?: run {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Tournament not found", color = Color.Gray)
            }
        }
    }
}
