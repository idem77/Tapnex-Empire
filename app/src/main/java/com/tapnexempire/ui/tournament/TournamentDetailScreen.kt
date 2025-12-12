package com.tapnexempire.ui.tournament

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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

data class TournamentDetail(
    val name: String,
    val prize: String,
    val entryFee: Int,
    val participants: Int
)

@Composable
fun TournamentDetailScreen(
    detail: TournamentDetail,
    onJoinClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightCream)
            .padding(16.dp)
    ) {
        Text(
            text = detail.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = CharcoalBlack
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Prize
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .shadow(4.dp, RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Box(
                modifier = Modifier.background(PinkPeachLight),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_crown),
                        contentDescription = "Prize",
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Prize: ${detail.prize}",
                        fontSize = 16.sp,
                        color = CharcoalBlack
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Entry Fee & Participants
        Text(
            text = "Entry Fee: ${detail.entryFee} coins",
            fontSize = 16.sp,
            color = CharcoalBlack
        )
        Text(
            text = "Participants: ${detail.participants}",
            fontSize = 16.sp,
            color = CharcoalBlack
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Join Button
        Button(
            onClick = onJoinClick,
            colors = ButtonDefaults.buttonColors(containerColor = Gold),
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text(
                text = "Join Tournament",
                fontSize = 16.sp,
                color = CharcoalBlack
            )
        }
    }
}
