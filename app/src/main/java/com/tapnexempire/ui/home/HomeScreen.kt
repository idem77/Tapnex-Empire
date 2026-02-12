package com.tapnexempire.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.R

@Composable
fun HomeScreen(
    onWalletClick: () -> Unit,
    onTournamentClick: () -> Unit,
    onTaskClick: () -> Unit
) {

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {

        val screenWidth = maxWidth
        val screenHeight = maxHeight

        // 👑 MASTER BACKGROUND
        Image(
            painter = painterResource(id = R.drawable.home_master),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        // 💰 COINS AREA (Top Center)
        Box(
            modifier = Modifier
                .size(screenWidth * 0.35f, screenHeight * 0.12f)
                .align(Alignment.TopCenter)
                .offset(y = screenHeight * 0.08f)
                .clickable { onWalletClick() }
        )

        // 🏆 TOURNAMENT AREA (Center)
        Box(
            modifier = Modifier
                .size(screenWidth * 0.7f, screenHeight * 0.18f)
                .align(Alignment.Center)
                .clickable { onTournamentClick() }
        )

        // 🏦 TREASURY AREA (Bottom Left)
        Box(
            modifier = Modifier
                .size(screenWidth * 0.4f, screenHeight * 0.15f)
                .align(Alignment.BottomStart)
                .offset(
                    x = screenWidth * 0.05f,
                    y = -(screenHeight * 0.08f)
                )
                .clickable { onWalletClick() }
        )

        // 📜 TASK AREA (Bottom Right)
        Box(
            modifier = Modifier
                .size(screenWidth * 0.4f, screenHeight * 0.15f)
                .align(Alignment.BottomEnd)
                .offset(
                    x = -(screenWidth * 0.05f),
                    y = -(screenHeight * 0.08f)
                )
                .clickable { onTaskClick() }
        )
    }
}
