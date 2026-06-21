package com.tapnexempire.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmpireTournamentCard(

    title: String,

    entryFee: Long,

    prizePool: Long,

    joinedPlayers: Long,

    maxPlayers: Long,

    status: String,

    onJoin: () -> Unit
) {

    Card(


modifier = Modifier
    .fillMaxWidth()
    .padding(vertical = 8.dp),

shape = RoundedCornerShape(28.dp),

colors = CardDefaults.cardColors(
    containerColor = Color.Transparent
),

elevation = CardDefaults.cardElevation(
    defaultElevation = 18.dp
)

) {

        Box(
    modifier = Modifier.fillMaxWidth()
) {

    Image(
        painter = painterResource(R.drawable.tournament_corner),
        contentDescription = null,
        modifier = Modifier
            .size(120.dp)
            .align(Alignment.TopStart)
    )

    Image(
        painter = painterResource(R.drawable.tournament_corner),
        contentDescription = null,
        modifier = Modifier
            .size(120.dp)
            .align(Alignment.TopEnd)
            .graphicsLayer {
                scaleX = -1f
            }
    )

Column(

    modifier = Modifier

        .background(

            brush = Brush.verticalGradient(

                colors = listOf(

                    Color(0xFF081B29),
                    Color(0xFF0E3A5D),
                    Color(0xFF1976D2)
                )
            )
        )

        .padding(22.dp)
) {

    Text(
        text = title,
        color = Color.White,
        fontSize = 22.sp
    )

    Spacer(modifier = Modifier.height(14.dp))

    Text(
        text = "🎟 Entry: $entryFee Coins",
        color = Color(0xFFFFE082)
    )

    Spacer(modifier = Modifier.height(6.dp))

    Text(
        text = "🏆 Prize Pool: $prizePool Coins",
        color = Color(0xFF80CBC4)
    )

    Spacer(modifier = Modifier.height(6.dp))

    Text(
        text = "👥 Players: $joinedPlayers/$maxPlayers",
        color = Color.White.copy(alpha = 0.85f)
    )

    Spacer(modifier = Modifier.height(6.dp))

    Text(
        text = "⚡ Status: $status",
        color = Color(0xFF81D4FA)
    )

    Spacer(modifier = Modifier.height(18.dp))

    Button(

        onClick = onJoin,

        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),

        shape = RoundedCornerShape(18.dp),

        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4FC3F7)
        )
    ) {

        Text(
            text = "Join Tournament",
            color = Color.Black,
            fontSize = 16.sp
        )
    }
}

    }
