package com.tapnexempire.ui.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.tapnexempire.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    isLoggedIn: Boolean = false // Replace with actual auth state
) {
    // Scale animation
    val scale = remember { Animatable(0.5f) }

    LaunchedEffect(true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)
        )
        delay(500) // Pause before navigation

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
            .background(Color(0xFFFFFBF5)) // Light cream background
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "Tapnex Empire Logo",
                modifier = Modifier
                    .scale(scale.value)
                    .size(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Optional tagline or small animation effect
            androidx.compose.material3.Text(
                text = "Empire of Games",
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFFFFC1C1) // Pink peach dark
            )
        }
    }
}
