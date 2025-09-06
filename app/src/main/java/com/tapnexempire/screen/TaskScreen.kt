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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.tapnexempire.ui.theme.RoyalTeal
import com.tapnexempire.ui.theme.VibrantCoral
import com.tapnexempire.ui.theme.SoftCream
import com.tapnexempire.ui.theme.White
import com.tapnexempire.components.GradientButton

@Composable
fun TaskScreen(
    onCollectReward: () -> Unit = {}
) {
    Surface(modifier = Modifier.fillMaxSize(), color = SoftCream) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Daily Tasks", fontWeight = FontWeight.Bold, fontSize = 22.sp)
            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                items(3) { index ->
                    TaskItem(
                        title = when (index) {
                            0 -> "Login Bonus"
                            1 -> "Play 1 Game"
                            else -> "Watch 1 Ad"
                        },
                        reward = when (index) {
                            0 -> "+50 Coins"
                            1 -> "+100 Coins"
                            else -> "+30 Coins"
                        },
                        onCollect = onCollectReward
                    )
                }
            }
        }
    }
}

@Composable
private fun TaskItem(title: String, reward: String, onCollect: () -> Unit) {
    val gradient = Brush.horizontalGradient(listOf(RoyalTeal, VibrantCoral))
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
                Spacer(modifier = Modifier.height(4.dp))
                Text(reward, color = VibrantCoral, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            }
            GradientButton(text = "Collect", modifier = Modifier.width(110.dp)) { onCollect() }
        }
    }
}
