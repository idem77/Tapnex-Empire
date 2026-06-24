package com.tapnexempire.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LeaderboardPlayerCard(

    rank: Int,

    username: String,

    points: Long

) {

    Card(

        modifier = Modifier.fillMaxWidth(),

        colors = CardDefaults.cardColors(

            containerColor =
                Color(0xFF102A43)

        )
    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            horizontalArrangement =
                Arrangement.SpaceBetween

        ) {

            Text(
                text = "#$rank"
            )

            Text(
                text = username
            )

            Text(
                text = "$points ⭐"
            )
        }
    }
}
