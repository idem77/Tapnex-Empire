package com.tapnexempire.ui.home

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.components.CoinCard
import com.tapnexempire.components.GameTile
import com.tapnexempire.components.RewardCard
import com.tapnexempire.ui.theme.Gold
import com.tapnexempire.ui.theme.LightCream
import com.tapnexempire.ui.theme.CharcoalBlack

@Composable
fun HomeScreen(
    coins: Int,
    onWalletClick: () -> Unit,
    onTournamentClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    var animateScale by remember { mutableStateOf(false) }
    val scaleAnim = remember { Animatable(0.8f) }

    LaunchedEffect(animateScale) {
        scaleAnim.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightCream)
            .padding(16.dp)
    ) {
        // Coins Card
        CoinCard(
            coins = coins,
            onClick = onWalletClick,
            modifier = Modifier
                .fillMaxWidth()
                .scale(scaleAnim.value)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Games Section
        Text(
            text = "Games",
            fontSize = 20.sp,
            color = CharcoalBlack,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        val games = listOf("Ludo", "Quiz", "Spin & Win", "Puzzle")
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(games) { game ->
                GameTile(
                    title = game,
                    onClick = { /* navigate to game */ }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Daily Tasks / Rewards Section
        Text(
            text = "Daily Rewards & Tasks",
            fontSize = 20.sp,
            color = CharcoalBlack,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        val tasks = listOf(
            "Daily Login" to 500,
            "Complete 3 Games" to 300,
            "Refer a Friend" to 700
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            tasks.forEach { task ->
                RewardCard(
                    title = task.first,
                    coins = task.second,
                    onClick = { /* claim or navigate */ }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom Navigation Placeholder
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Home", color = CharcoalBlack, modifier = Modifier.clickable { })
            Text("Wallet", color = CharcoalBlack, modifier = Modifier.clickable { onWalletClick() })
            Text("Tournaments", color = CharcoalBlack, modifier = Modifier.clickable { onTournamentClick() })
            Text("Profile", color = CharcoalBlack, modifier = Modifier.clickable { onProfileClick() })
        }
    }
}
