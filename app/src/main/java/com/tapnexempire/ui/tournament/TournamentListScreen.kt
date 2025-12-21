package com.tapnexempire.ui.tournament

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
    viewModel: TournamentViewModel = hiltViewModel()
) {
    val tournaments by viewModel.tournaments.collectAsState()

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

        if (tournaments.isEmpty()) {
            Text(
                text = "No tournaments available",
                color = CharcoalBlack,
                fontSize = 16.sp
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(tournaments) { tournament ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.joinTournament(tournament.id)
                            },
                        colors = CardDefaults.cardColors(containerColor = Gold.copy(alpha = 0.15f))
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = tournament.name,
                                fontSize = 18.sp,
                                color = CharcoalBlack
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Entry: ${tournament.entryFee} coins",
                                fontSize = 14.sp,
                                color = CharcoalBlack
                            )
                            Text(
                                text = "Prize: ${tournament.prize} coins",
                                fontSize = 14.sp,
                                color = CharcoalBlack
                            )
                        }
                    }
                }
            }
        }
    }
}
