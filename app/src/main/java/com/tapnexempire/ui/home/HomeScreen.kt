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
    val userId = FirebaseAuth.getInstance().currentUser?.uid
    val wallet by walletViewModel.walletState.collectAsState()

    LaunchedEffect(userId) {
        userId?.let { walletViewModel.loadWallet(it) }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        // 🌌 BACKGROUND IMAGE (Cultivation Empire)
        Image(
            painter = painterResource(id = R.drawable.home_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // 🔮 DARK OVERLAY (readability ke liye)
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
                        containerColor = Color.Black.copy(alpha = 0.6f)
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
                            text = wallet?.totalEarnings?.toString() ?: "0",
                            fontSize = 30.sp,
                            color = Color(0xFFFFD700) // Gold
                        )

                        Spacer(modifier = Modifier.height(10.dp))

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
