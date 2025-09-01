package com.tapnexempire.screens.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.ui.theme.NeonBlue

@Composable
fun WalletScreen() {
    val balance = 1250 // dummy balance
    val transactions = listOf(
        "Added 500 Coins",
        "Redeemed 200 Coins",
        "Reward +50 Coins",
        "Task Completed +100 Coins"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Balance Card
        Card(
            colors = CardDefaults.cardColors(containerColor = NeonBlue),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = MaterialTheme.shapes.extraLarge,
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Your Balance",
                    color = Color.White,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$balance Coins",
                    color = Color.Yellow,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Buttons
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { /* Add Coins Action */ },
                colors = ButtonDefaults.buttonColors(containerColor = NeonBlue),
                shape = MaterialTheme.shapes.large
            ) {
                Text("Add Coins", color = Color.White)
            }
            Button(
                onClick = { /* Redeem Action */ },
                colors = ButtonDefaults.buttonColors(containerColor = NeonBlue),
                shape = MaterialTheme.shapes.large
            ) {
                Text("Redeem", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Recent Transactions
        Text(
            text = "Recent Transactions",
            color = NeonBlue,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(transactions) { item ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = item,
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
    }
}
