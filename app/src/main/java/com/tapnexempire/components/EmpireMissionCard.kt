package com.tapnexempire.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmpireMissionCard(

    title: String,

    progress: Float,

    rewardCoins: Int,

    onClaim: () -> Unit
) {

    Card(

        modifier = Modifier
            .fillMaxWidth(),

        shape = RoundedCornerShape(28.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {

        Column(

            modifier = Modifier

                .background(

                    brush = Brush.verticalGradient(

                        colors = listOf(

                            Color(0xFF23262F),

                            Color(0xFF16181D)
                        )
                    )
                )

                .padding(22.dp)
        ) {

            Text(

                text = title,

                color = Color.White,

                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            LinearProgressIndicator(

                progress = { progress },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp),

                color = Color(0xFFFFC107),

                trackColor = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(

                text = "🎁 Reward: $rewardCoins Coins",

                color = Color(0xFFFFD54F),

                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(18.dp))

            Button(

                onClick = onClaim,

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(18.dp),

                colors = ButtonDefaults.buttonColors(

                    containerColor =
                        Color(0xFFFFC107)
                )
            ) {

                Text(
                    text = "Claim Reward",
                    color = Color.Black
                )
            }
        }
    }
}
