package com.tapnexempire.ui.tournament

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.R

// Sample data model
data class Tournament(
    val id: Int,
    val name: String,
    val entryFee: String
)

@Composable
fun TournamentListScreen(
    tournaments: List<Tournament>,
    onTournamentClick: (Tournament) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFBF5))
            .padding(16.dp)
    ) {
        Text(
            text = "Tournaments",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333)
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tournaments) { tournament ->
                TournamentItem(tournament, onTournamentClick)
            }
        }
    }
}

@Composable
fun TournamentItem(
    tournament: Tournament,
    onClick: (Tournament) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable { onClick(tournament) },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE0E0))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = tournament.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Entry Fee: ${tournament.entryFee}",
                    fontSize = 14.sp,
                    color = Color(0xFF666666)
                )
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_crown),
                contentDescription = "Join Tournament",
                tint = Color(0xFFFFC107),
                modifier = Modifier.size(40.dp)
            )
        }
    }
}
