package com.tapnexempire.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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

        // 👑 Empire Header
        Text(
            text = "Tapnex Empire 👑",
            fontSize = 28.sp,
            color = Gold
        )

        Text(
            text = "Cultivator Rank: Mortal Realm 🌱",
            fontSize = 14.sp,
            color = CharcoalBlack
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 💰 Empire Wallet Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "Empire Balance 💰",
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = (wallet?.totalEarnings ?: 0).toString(),
                    fontSize = 30.sp,
                    color = Gold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Bonus + Deposit + Wins",
                    fontSize = 12.sp,
