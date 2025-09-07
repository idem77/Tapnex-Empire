package com.tapnexempire.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapnexempire.ui.theme.RoyalTeal
import com.tapnexempire.ui.theme.White
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(2000) // ⏳ 2s splash delay
        onTimeout()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = RoyalTeal
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Tapnex Empire",
                style = MaterialTheme.typography.headlineLarge,
                color = White
            )
        }
    }
}
