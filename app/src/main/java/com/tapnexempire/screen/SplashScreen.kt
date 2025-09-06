package com.tapnexempire.screen

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import com.tapnexempire.ui.theme.RoyalTeal
import com.tapnexempire.ui.theme.VibrantCoral
import com.tapnexempire.ui.theme.White

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    // small entrance animation then navigate
    var start by remember { mutableStateOf(false) }
    val scale = animateFloatAsState(if (start) 1f else 0.85f, animationSpec = tween(700, easing = FastOutSlowInEasing))

    LaunchedEffect(Unit) {
        start = true
        delay(1500) // keep for 1.5s, then navigate
        onTimeout()
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(RoyalTeal),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // badge / logo
            val gradient = Brush.verticalGradient(listOf(RoyalTeal, VibrantCoral))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size((160 * scale.value).dp)
                        .shadow(12.dp, RoundedCornerShape(32.dp))
                        .background(brush = gradient, shape = RoundedCornerShape(32.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("TAPNEX\nEMPIRE", color = White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
