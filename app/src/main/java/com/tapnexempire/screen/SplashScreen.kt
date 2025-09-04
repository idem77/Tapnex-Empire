package com.tapnexempire.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.navigation.NavController
import com.tapnexempire.ui.theme.*

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2500) // 2.5 seconds
        navController.navigate("home") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(MidnightBlack, RoyalBlue)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // App Logo Placeholder
            Text(
                text = "Tapnex Empire",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Gold
            )

            Spacer(modifier = Modifier.height(16.dp))

            CircularProgressIndicator(
                color = NeonGreen,
                strokeWidth = 3.dp
            )
        }
    }
}
