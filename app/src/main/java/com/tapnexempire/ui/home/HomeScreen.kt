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
    onTaskClick: () -> Unit
) {

    // 🔐 TEMP SAFE COINS (billing/firebase later)
    val qiCoins by remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {

        // 🌌 BACKGROUND IMAGE
        Image(
            painter = painterResource(id = R.drawable.home_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                // 👑 HEADER
                Column {
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
                }

                // 💰 COIN CARD
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Black.copy(alpha = 0.65f)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "Qi Coins",
                            color = Color.LightGray
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = qiCoins.toString(),
                            fontSize = 32.sp,
                            color = Color(0xFFFFD700)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(onClick = onWalletClick) {
                            Text("Open Treasury")
                        }
                    }
                }

                // 🏆 ACTION BUTTONS
                Column {
                    Button(
                        onClick = onTournamentClick,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Enter Tournament Realm 🏆")
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = onTaskClick,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Daily Cultivation Tasks 📜")
                    }
                }
            }
        }
    }
}
