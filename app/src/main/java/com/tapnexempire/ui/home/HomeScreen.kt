package com.tapnexempire.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.ui.theme.CharcoalBlack
import com.tapnexempire.ui.theme.Gold
import com.tapnexempire.viewmodel.WalletViewModel
import com.google.firebase.auth.FirebaseAuth

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Tapnex Empire 👑",
            fontSize = 26.sp,
            color = CharcoalBlack
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 💰 Wallet Card
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Total Coins", fontSize = 16.sp)

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = wallet?.totalEarnings?.toString() ?: "0",
                    fontSize = 28.sp,
                    color = Gold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onWalletClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Gold)
                ) {
                    Text("Open Wallet", color = CharcoalBlack)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🎯 Action Buttons
        HomeActionButton(
            title = "Tournaments 🏆",
            description = "Join & win coins",
            onClick = onTournamentClick
        )

        Spacer(modifier = Modifier.height(12.dp))

        HomeActionButton(
            title = "Tasks 📋",
            description = "Complete & earn",
            onClick = onTaskClick
        )
    }
}
