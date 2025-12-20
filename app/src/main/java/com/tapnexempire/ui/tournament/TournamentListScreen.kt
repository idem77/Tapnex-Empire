package com.tapnexempire.ui.tournament

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.ui.theme.Gold
import com.tapnexempire.ui.theme.CardBackground
import com.tapnexempire.ui.theme.CharcoalBlack

data class Tournament(
    val id: Int,
    val name: String,
    val prize: String
)

@Composable
fun TournamentListScreen(
    tournaments: List<Tournament>,
    onTournamentClick: (Tournament) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFBF5)) // Light cream background
            .padding(16.dp)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tournaments) { tournament ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onTournamentClick(tournament) },
                    backgroundColor = CardBackground
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = tournament.name,
                                fontSize = 18.sp,
                                color = CharcoalBlack,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Prize: ${tournament.prize}",
                                fontSize = 14.sp,
                                color = Gold,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Text(
                            text = "Join",
                            fontSize = 16.sp,
                            color = Gold,
                            modifier = Modifier
                                .background(Gold.copy(alpha = 0.1f))
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }
            }
        }
    }
}
