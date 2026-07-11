package com.tapnexempire.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.R

@Composable
fun EmpireTournamentCard(

title: String,

entryFee: Long,

prizePool: Long,

joinedPlayers: Long,

maxPlayers: Long,

endTime: Long,

status: String,

onJoin: () -> Unit

) {

    var currentTime by remember {
    mutableStateOf(System.currentTimeMillis())
}

LaunchedEffect(Unit) {

    while (true) {

        currentTime = System.currentTimeMillis()

        kotlinx.coroutines.delay(1000)
    }
}

val remainingMillis =
    endTime - currentTime

val remainingMinutes =
    (remainingMillis / 1000 / 60)

val remainingSeconds =
    (remainingMillis / 1000) % 60

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
                .size(116.dp)
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

                .fillMaxWidth()

                .background(

                    brush = Brush.verticalGradient(

                        colors = listOf(

                            Color(0xFF081B29),

                            Color(0xFF0E3A5D),

                            Color(0xFF1976D2)
                        )
                    )
                )

                .padding(20.dp)
        ) {

            Row(
    modifier = Modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically
) {

    Text(
        text = title,
        color = Color.White,
        fontSize = 22.sp,
        modifier = Modifier.weight(1f)
    )

    Image(
        painter = painterResource(R.drawable.empire_king),
        contentDescription = null,
        modifier = Modifier.size(58.dp)
    )
            }

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

    text =
        if (remainingMillis > 0)
            "⏳ ${remainingMinutes}m ${remainingSeconds}s Left"
        else
            "⏳ Tournament Finished",

    color = Color(0xFFFFE082)
)
            
            Text(
                text = "⚡ Status: $status",
                color = Color(0xFF81D4FA)
            )

            Spacer(modifier = Modifier.height(18.dp))

            Button(

                onClick = onJoin,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),

                shape = RoundedCornerShape(20.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1E88E5)
                )
            ) {

                Text(
                    text = "Join Tournament",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}

}
