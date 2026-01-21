package com.tapnexempire.ui.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tapnexempire.R
import kotlinx.coroutines.delay

package com.tapnexempire.ui.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.tapnexempire.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateNext: (Boolean) -> Unit
) {
    val scale = remember { Animatable(0.8f) }
    val auth = FirebaseAuth.getInstance()

    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1800,
                easing = FastOutSlowInEasing
            )
        )

        delay(700) // Firebase ko saans lene do 😌

        val isLoggedIn = auth.currentUser != null
        onNavigateNext(isLoggedIn)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Tapnex Empire Logo",
            modifier = Modifier
                .scale(scale.value)
                .size(180.dp)
        )
    }
}
