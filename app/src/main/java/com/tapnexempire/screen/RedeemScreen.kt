package com.tapnexempire.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.tapnexempire.ui.theme.RoyalTeal
import com.tapnexempire.ui.theme.VibrantCoral
import com.tapnexempire.ui.theme.SoftCream
import com.tapnexempire.ui.theme.White
import com.tapnexempire.components.GradientButton

@Composable
fun RedeemScreen(
    balanceCoins: Int = 2340,
    onRedeem: (coins: Int) -> Unit = {}
) {
    Surface(modifier = Modifier.fillMaxSize(), color = SoftCream) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Redeem Coins", fontWeight = FontWeight.Bold, fontSize = 22.sp)
            Spacer(modifier = Modifier.height(20.dp))

            // Balance card
            val gradient = Brush.horizontalGradient(listOf(RoyalTeal, VibrantCoral))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(10.dp, RoundedCornerShape(20.dp))
                    .background(brush = gradient, shape = RoundedCornerShape(20.dp))
                    .padding(20.dp)
            ) {
                Column {
                    Text("Your Balance", color = White.copy(0.9f))
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("$balanceCoins Coins", color = White, fontWeight = FontWeight.Bold, fontSize = 26.sp)
                    Text("≈ ₹${balanceCoins / 100}", color = White.copy(0.8f), fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(26.dp))

            // Redeem options
            RedeemOption("₹10 Paytm", 1000, onRedeem)
            Spacer(modifier = Modifier.height(14.dp))
            RedeemOption("₹50 Paytm", 5000, onRedeem)
            Spacer(modifier = Modifier.height(14.dp))
            RedeemOption("₹100 Paytm", 10000, onRedeem)
        }
    }
}

@Composable
private fun RedeemOption(title: String, coins: Int, onRedeem: (coins: Int) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(18.dp))
            .background(White, RoundedCornerShape(18.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text("$coins Coins", color = VibrantCoral, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            }
            GradientButton(text = "Redeem", modifier = Modifier.width(120.dp)) {
                onRedeem(coins)
            }
        }
    }
}
