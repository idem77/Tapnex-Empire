package com.tapnexempire.ui.tournament

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun MyTournamentScreen(
    onTournamentClick: (Tournament) -> Unit
) {
    val tournamentViewModel: TournamentViewModel = hiltViewModel()
    val myTournaments = tournamentViewModel.getMyTournaments().collectAsState(initial = emptyList()).value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightCream)
            .padding(16.dp)
    ) {
        Text(
            text = "My Tournaments",
            fontSize = 24.sp,
            color = CharcoalBlack,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (myTournaments.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "You haven't joined any tournaments yet.", color = CharcoalBlack)
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(myTournaments) { tournament ->
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onTournamentClick(tournament) },
                        colors = CardDefaults.cardColors(containerColor = Gold.copy(alpha = 0.15f))
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(text = tournament.name, fontSize = 18.sp, color = CharcoalBlack)
                            Text(text = "Prize: ${tournament.prize}", fontSize = 14.sp, color = CharcoalBlack.copy(alpha = 0.7f))
                            Text(text = "Entry Fee: ${tournament.entryFee}", fontSize = 14.sp, color = CharcoalBlack.copy(alpha = 0.7f))
                        }
                    }
                }
            }
        }
    }
}
