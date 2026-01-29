package com.tapnexempire.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.auth.FirebaseAuth
import com.tapnexempire.R
import com.tapnexempire.viewmodel.WalletViewModel

@Composable
fun HomeScreen(
    onWalletClick: () -> Unit,
    onTournamentClick: () -> Unit,
    onTaskClick: () -> Unit,
    walletViewModel: WalletViewModel = hiltViewModel()
) {
    // 🔐 TEMP SAFE COINS (billing/firebase later)
    val qiCoins by remember { mutableStateOf(0) }
     val wallet= null
    Box(modifier = Modifier.fillMaxSize()) {
    

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
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // 👑 HEADER
            Column {
                Text(
                    text = "Tapnex Empire 👑",
                    fontSize = 28.sp,
                    color = Color.White
                )
                Text(
                    text = "Cultivation Path Begins",
                    fontSize = 14.sp,
                    color = Color.LightGray
                )
            }

            // 💰 QI CARD
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black.copy(alpha = 0.55f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Current Qi", color = Color.LightGray)
                    Text(
                        text = wallet?.totalEarnings?.toString() ?: "0",
                        fontSize = 30.sp,
                        color = Color(0xFFFFD700)
                    )
                }
            }

            // 🏦 TREASURY IMAGE
            HomeImageCard(
                image = R.drawable.home_treasury,
                title = "Treasury",
                onClick = onWalletClick
            )

            // 🏆 TOURNAMENT IMAGE
            HomeImageCard(
                image = R.drawable.home_tournament,
                title = "Tournament Hall",
                onClick = onTournamentClick
            )

            // 📜 TASK IMAGE
            HomeImageCard(
                image = R.drawable.home_tasks,
                title = "Daily Tasks",
                onClick = onTaskClick
            )
        }
    }
}

@Composable
private fun HomeImageCard(
    image: Int,
    title: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clip(RoundedCornerShape(18.dp))
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}
