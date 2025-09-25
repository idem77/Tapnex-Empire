package com.tapnexempire.ui.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.R
import com.tapnexempire.ui.theme.PinkPeachLight
import com.tapnexempire.ui.theme.CharcoalBlack
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onTimeout: () -> Unit
) {
    // ✅ Fade-in infinite animation
    val alphaAnim = rememberInfiniteTransition()
    val alpha by alphaAnim.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PinkPeachLight),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // ✅ App Logo
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "Tapnex Empire Logo",
                modifier = Modifier
                    .size(150.dp)
                    .alpha(alpha)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // ✅ Tagline
            Text(
                text = "Play • Earn • Rule the Empire",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 18.sp,
                color = CharcoalBlack
            )
        }
    }

    // ✅ Navigate after delay (2 sec)
    LaunchedEffect(Unit) {
        delay(2000)
        onTimeout()
    }
}
