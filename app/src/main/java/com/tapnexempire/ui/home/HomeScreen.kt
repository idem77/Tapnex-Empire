package com.tapnexempire.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.R

@Composable
fun HomeScreen(
    onWalletClick: () -> Unit,
    onTournamentClick: () -> Unit,
    onTaskClick: () -> Unit,
    walletViewModel: WalletViewModel = hiltViewModel()
) {
    // 🔐 TEMP SAFE COINS (billing/firebase later)
    val qiCoins by remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {

    LaunchedEffect(userId) {

        // 🌌 BACKGROUND
        Image(
            painter = painterResource(id = R.drawable.home_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {

            // 👑 HEADER
            Text(
                text = "Tapnex Empire 👑",
                fontSize = 28.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Cultivation Path Begins",
                fontSize = 14.sp,
                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 💰 TREASURY (IMAGE CLICK)
            EmpireActionImage(
                imageRes = R.drawable.treasury,
                onClick = onWalletClick
            )

            // 🏆 TOURNAMENT HALL
            EmpireActionImage(
                imageRes = R.drawable.tournament_hall,
                onClick = onTournamentClick
            )

            // 📜 DAILY TASKS
            EmpireActionImage(
                imageRes = R.drawable.daily_tasks,
                onClick = onTaskClick
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 👑 COIN DISPLAY (OPTIONAL)
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black.copy(alpha = 0.6f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Qi Coins", color = Color.LightGray)

                    Text(
                        text = wallet?.totalEarnings?.toString() ?: "0",
                        fontSize = 30.sp,
                        color = Color(0xFFFFD700)
                    )
                }
            }
        }
    }
}
