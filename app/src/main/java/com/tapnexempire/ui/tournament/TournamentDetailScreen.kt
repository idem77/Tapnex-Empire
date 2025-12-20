package com.tapnexempire.ui.tournament

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.ui.theme.CardBackground
import com.tapnexempire.ui.theme.Gold
import com.tapnexempire.ui.theme.CharcoalBlack

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
            .background(Color(0xFFFFFBF5))
            .padding(16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = CardBackground)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = detail.name,
                    fontSize = 22.sp,
                    color = CharcoalBlack,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Prize: ${detail.prize}",
                    fontSize = 18.sp,
                    color = Gold,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Entry Fee: ${detail.entryFee} coins",
                    fontSize = 16.sp,
                    color = CharcoalBlack
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Participants: ${detail.participants}",
                    fontSize = 16.sp,
                    color = CharcoalBlack
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { onJoinClick() },
                    colors = ButtonDefaults.buttonColors(containerColor = Gold),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Join Tournament", color = CharcoalBlack)
                }
            }
        }
    }
}
