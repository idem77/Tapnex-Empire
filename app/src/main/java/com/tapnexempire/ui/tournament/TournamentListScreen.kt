package com.tapnexempire.ui.tournament

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.tapnexempire.R
import com.tapnexempire.ui.theme.*

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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightCream)
            .padding(16.dp)
    ) {
        Text(
            text = "Tournaments",
            color = CharcoalBlack,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))

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
fun TournamentItem(tournament: Tournament, onClick: (Tournament) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable { onClick(tournament) }
            .shadow(4.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .background(PinkPeachLight),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_crown),
                    contentDescription = "Tournament",
                    modifier = Modifier.size(36.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = tournament.name,
                        color = CharcoalBlack,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "Prize: ${tournament.prize}",
                        color = SoftGray,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
