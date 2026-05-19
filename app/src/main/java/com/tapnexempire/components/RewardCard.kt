package com.tapnexempire.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RewardCard(

    rewardTitle: String,

    rewardAmount: Int,

    onClaim: () -> Unit
) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),

        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(

            containerColor =
                Color(0xFF1A1C22)
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {

        Row(

            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),

            verticalAlignment = Alignment.CenterVertically,

            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            Column {

                Text(

                    text = rewardTitle,

                    color = Color.White,

                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(

                    text = "🎁 Reward: $rewardAmount",

                    color = Color(0xFFFFD54F),

                    fontSize = 20.sp
                )
            }
        }
    }
}
