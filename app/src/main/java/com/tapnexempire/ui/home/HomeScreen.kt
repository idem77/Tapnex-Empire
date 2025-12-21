package com.tapnexempire.ui.home

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.components.CoinCard
import com.tapnexempire.components.GameTile
import com.tapnexempire.ui.theme.CharcoalBlack
import com.tapnexempire.ui.theme.LightCream
import com.tapnexempire.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    onWalletClick: () -> Unit,
    onTournamentClick: () -> Unit,
    onProfileClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val walletState by viewModel.walletState.collectAsState()

    val scaleAnim = remember { Animatable(0.8f) }

    LaunchedEffect(Unit) {
        scaleAnim.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = FastOutSlowInEasing
            )
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
            coins = walletState.totalCoins,
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
                    onClick = {
                        // future: navigate to game
                    }
                )
            }
        }
    }
}
