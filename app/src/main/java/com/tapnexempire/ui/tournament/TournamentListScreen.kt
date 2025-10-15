package com.tapnexempire.ui.tournament

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tapnexempire.viewmodel.TournamentViewModel
import com.tapnexempire.models.TournamentModel

@Composable
fun TournamentListScreen(
    onTournamentClick: (String) -> Unit,
    viewModel: TournamentViewModel = viewModel()
) {
    val tournaments by viewModel.tournaments.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Tournaments", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF101820),
                    titleContentColor = Color.White
                )
            )
        },
        containerColor = Color(0xFFF8F9FA)
    ) { padding ->

        if (tournaments.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Loading tournaments...", color = Color.Gray)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 16.dp)
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

@Composable
fun TournamentCard(tournament: TournamentModel, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = tournament.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF101820)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Entry Fee: ${tournament.entryFee} coins",
                color = Color(0xFF5C5C5C),
                fontSize = 14.sp
            )

            Text(
                text = "Players: ${tournament.joinedPlayers}/${tournament.totalPlayers}",
                color = Color(0xFF5C5C5C),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            val joinText = when {
                tournament.isFull -> "Full"
                tournament.isJoined -> "Joined"
                else -> "Join Now"
            }

            Button(
                onClick = onClick,
                enabled = !tournament.isFull && !tournament.isJoined,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (tournament.isJoined) Color.Gray else Color(0xFF007BFF)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(joinText, color = Color.White)
            }
        }
    }
}
