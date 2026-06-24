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

    val title = when(rank) {

        1 -> "🌊 Ocean Emperor"

        2 -> "⚡ Tide Master"

        3 -> "🔥 Deep Sea Lord"

        in 4..10 -> "🏆 Elite Challenger"

        else -> "⭐ Empire Warrior"
    }

    Card(

        modifier = Modifier.fillMaxWidth(),

        colors = CardDefaults.cardColors(

            containerColor =
                Color(0xFF102A43)

        )

    ) {

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)

        ) {

            Text(
                text = "#$rank  $username",
                color = Color.White
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = title,
                color = Color(0xFF81D4FA)
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "🔥 Competing For Weekly Rewards",
                color = Color(0xFFFFE082)
            )
        }
    }
}
