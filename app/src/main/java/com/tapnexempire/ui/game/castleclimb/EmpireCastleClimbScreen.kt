package com.tapnexempire.ui.game.castleclimb

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun EmpireCastleClimbScreen() {

    var score by remember {
        mutableStateOf(0)
    }

    var heroOffset by remember {
        mutableStateOf(0f)
    }

    var timeLeft by remember {
        mutableStateOf(30)
    }

    var gameOver by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {

        while (timeLeft > 0) {

            delay(1000)

            timeLeft--
        }

        gameOver = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                androidx.compose.ui.graphics.Color.Black
            )
            .clickable {

                if (!gameOver) {

                    score++

                    heroOffset += 8f
                }
            }
    ) {

        // 🏰 Castle
        Text(
            text = "🏰",
            fontSize = 40.sp,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 40.dp)
        )

        // ⭐ Score
        Text(
            text = "Score: $score",
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        )

        // ⏱ Timer
        Text(
            text = "Time: $timeLeft",
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        )

        // 👑 Hero
        Text(
            text = "👑",
            fontSize = 36.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(
                    y = (-heroOffset).dp
                )
        )

        if (gameOver) {

            Text(
                text =
                    "🏆 Game Over\nScore: $score",

                fontSize = 28.sp,

                modifier = Modifier.align(
                    Alignment.Center
                )
            )
        }
    }
}
