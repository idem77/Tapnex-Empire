package com.tapnexempire.ui.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import com.tapnexempire.R

@Composable
fun SplashScreen(
    navController: NavController,
    isLoggedIn: Boolean = false // Replace with actual auth check if needed
) {
    // Animation states
    val scale = remember { Animatable(0.8f) }

    // Launch animation
    LaunchedEffect(true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 2500, easing = FastOutSlowInEasing)
        )
        delay(500) // Extra pause after animation

        // Navigate to next screen
        if (isLoggedIn) {
            navController.navigate("home") {
                popUpTo("splash") { inclusive = true }
            }
        } else {
            navController.navigate("otpLogin") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    // UI
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo), // Replace with your logo PNG
            contentDescription = "Tapnex Empire Logo",
            modifier = Modifier
                .scale(scale.value)
                .size(180.dp)
        )
    }
}
