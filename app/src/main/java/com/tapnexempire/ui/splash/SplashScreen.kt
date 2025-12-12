package com.tapnexempire.ui.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import com.tapnexempire.R

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {
    var visible by remember { mutableStateOf(false) }

    // Animation: Fade + Scale
    val alphaAnim = animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(1200),
        label = ""
    )

    val scaleAnim = animateFloatAsState(
        targetValue = if (visible) 1f else 0.7f,
        animationSpec = tween(1200),
        label = ""
    )

    LaunchedEffect(true) {
        visible = true
        delay(2000)
        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // 🔥 YOUR LOGO (Replace splash_logo with your real png)
            Image(
                painter = painterResource(id = R.drawable.splash_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(180.dp)
                    .alpha(alphaAnim.value)
                    .scale(scaleAnim.value)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Tapnex Empire",
                fontSize = 28.sp,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.alpha(alphaAnim.value)
            )
        }
    }
}
