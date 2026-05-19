package com.tapnexempire.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmpireLeaderboardCard(

    rank: Int,

    username: String,

    coins: Long
) {

    Card(

        modifier = Modifier
            .fillMaxWidth(),

        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {

        Row(

            modifier = Modifier

                .background(

                    brush = Brush.horizontalGradient(

                        colors = listOf(

                            Color(0xFF1F222B),

                            Color(0xFF14161B)
                        )
                    )
                )

                .fillMaxWidth()

                .padding(18.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(

                modifier = Modifier

                    .size(52.dp)

                    .background(

                        color = Color(0xFFFFC107),

                        shape = CircleShape
                    ),

                contentAlignment = Alignment.Center
            ) {

                Text(

                    text = "#$rank",

                    color = Color.Black,

                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.width(18.dp))

            Column(

                modifier = Modifier.weight(1f)
            ) {

                Text(

                    text = username,

                    color = Color.White,

                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(

                    text = "🪙 $coins Coins",

                    color = Color(0xFFFFD54F),

                    fontSize = 16.sp
                )
            }
        }
    }
}
