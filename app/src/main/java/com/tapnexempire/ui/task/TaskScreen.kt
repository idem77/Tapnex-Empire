package com.tapnexempire.ui.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.ui.theme.Gold
import com.tapnexempire.ui.theme.LightCream

@Composable
fun TaskScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightCream)
            .padding(16.dp)
    ) {

        Text(
            text = "Tasks & Rewards",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        TaskItem(
            icon = Icons.Default.Login,
            title = "Daily Login Bonus",
            reward = " +500 Coins",
            buttonText = "Claim"
        )

        TaskItem(
            icon = Icons.Default.PlayCircle,
            title = "Watch Ad",
            reward = " +200 Coins",
            buttonText = "Watch"
        )

        TaskItem(
            icon = Icons.Default.SportsEsports,
            title = "Play Tournament",
            reward = " +1000 Coins",
            buttonText = "Play"
        )

        TaskItem(
            icon = Icons.Default.Share,
            title = "Refer a Friend",
            reward = " +2000 Coins",
            buttonText = "Invite"
        )
    }
}

@Composable
private fun TaskItem(
    icon: ImageVector,
    title: String,
    reward: String,
    buttonText: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                icon,
                contentDescription = null,
                tint = Gold,
                modifier = Modifier.size(30.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = title, fontSize = 16.sp)
                Text(
                    text = reward,
                    fontSize = 14.sp,
                    color = Gold
                )
            }

            Button(
                onClick = { /* future action */ },
                colors = ButtonDefaults.buttonColors(containerColor = Gold),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = buttonText, color = Color.Black)
            }
        }
    }
}
