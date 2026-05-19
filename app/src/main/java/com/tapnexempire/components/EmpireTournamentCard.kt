package com.tapnexempire.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
            containerColor =
                Color(0xFF1A1C22).copy(alpha = 0.92f)
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        )
    ) {

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            Text(

                text = title,

                color = Color.White,

                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "🎟 Entry: $entryFee Coins",
                color = Color(0xFFFFD54F)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "🏆 Prize Pool: $prizePool Coins",
                color = Color(0xFF81C784)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text =
                    "👥 Players: $joinedPlayers/$maxPlayers",

                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "⚡ Status: $status",
                color = Color(0xFF64B5F6)
            )

            Spacer(modifier = Modifier.height(18.dp))

            Button(

                onClick = onJoin,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),

                shape = RoundedCornerShape(18.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        Color(0xFFFFB300)
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
}
