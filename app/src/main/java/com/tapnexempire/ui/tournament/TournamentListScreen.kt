package com.tapnexempire.ui.tournament

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.ui.theme.LightCream
import com.tapnexempire.ui.theme.CharcoalBlack
import com.tapnexempire.ui.theme.Gold
import com.tapnexempire.viewmodel.TournamentViewModel

@Composable
fun TournamentListScreen(
    onTournamentClick: (Tournament) -> Unit
) {
    val tournamentViewModel: TournamentViewModel = hiltViewModel()
    val tournaments = tournamentViewModel.tournamentState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightCream)
            .padding(16.dp)
    ) {
        Text(
            text = "Tournaments",
            fontSize = 24.sp,
            color = CharcoalBlack,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tournaments) { tournament ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onTournamentClick(tournament) },
                    colors = CardDefaults.cardColors(containerColor = Gold.copy(alpha = 0.15f))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = tournament.name, color = CharcoalBlack, fontSize = 18.sp)
                            Text(text = "Prize: ${tournament.prize}", color = CharcoalBlack.copy(alpha = 0.7f), fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}

// Sample data class
data class Tournament(
    val id: Int,
    val name: String,
    val prize: String
)
