package com.tapnexempire.ui.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import com.tapnexempire.R

@Composable
fun SplashScreen(onTimeout: () -> Unit) {

    // ⭐ Animation Setup
    val scale = remember { Animatable(0.6f) }

    LaunchedEffect(true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1200,
                easing = { OvershootEasing(it) }
            )
        )

        delay(2500)
        onTimeout()
    }

    // ⭐ UI
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFE0E0)),   // Pink Peach Light
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            // Logo / Icon
            Image(
                painter = painterResource(id = R.drawable.tapnex_logo), // Replace with your logo
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(180.dp)
                    .scale(scale.value)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // App Name
            androidx.compose.material3.Text(
                text = "Tapnex Empire",
                color = Color(0xFF333333),
                style = androidx.compose.material3.MaterialTheme.typography.headlineMedium
            )
        }
    }
}

// Overshoot increase
fun OvershootEasing(t: Float): Float {
    val tension = 2.3f
    return (t - 1) * (t - 1) * ((tension + 1) * (t - 1) + tension) + 1
}
