package com.tapnexempire.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Brush
import com.tapnexempire.ui.theme.RoyalTeal
import com.tapnexempire.ui.theme.VibrantCoral
import com.tapnexempire.ui.theme.SoftCream
import com.tapnexempire.ui.theme.White
import com.tapnexempire.components.GradientButton

@Composable
fun TournamentScreen(
    onJoinClick: (String) -> Unit = {}
) {
    Surface(modifier = Modifier.fillMaxSize(), color = SoftCream) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Tournaments", fontWeight = FontWeight.Bold, fontSize = 22.sp)
            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                items(3) { index ->
                    val name = when (index) {
                        0 -> "Ludo Classic Cup"
                        1 -> "Tapnex Weekend Challenge"
                        else -> "Mega Empire League"
                    }
                    val prize = when (index) {
                        0 -> "Win 5,000 Coins"
                        1 -> "Win 20,000 Coins"
                        else -> "Win 50,000 Coins"
                    }
                    TournamentItem(name, prize) { onJoinClick(name) }
                }
            }
        }
    }
}

@Composable
private fun TournamentItem(title: String, prize: String, onJoin: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(18.dp))
            .background(White, RoundedCornerShape(18.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Text(prize, color = VibrantCoral, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            }
            GradientButton(text = "Join", modifier = Modifier.width(100.dp)) { onJoin() }
        }
    }
}
