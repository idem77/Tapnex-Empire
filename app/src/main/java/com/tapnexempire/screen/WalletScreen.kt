package com.tapnexempire.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun WalletScreen(
    onRedeemClick: () -> Unit = {}
) {
    Surface(modifier = Modifier.fillMaxSize(), color = SoftCream) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("My Wallet", fontWeight = FontWeight.Bold, fontSize = 22.sp)
            Spacer(modifier = Modifier.height(20.dp))

            val gradient = Brush.horizontalGradient(listOf(RoyalTeal, VibrantCoral))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .shadow(10.dp, RoundedCornerShape(24.dp))
                    .background(brush = gradient, shape = RoundedCornerShape(24.dp))
                    .padding(20.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Text("Available Balance", color = White.copy(0.9f), fontSize = 16.sp)
                    Text("2,340 Coins", color = White, fontWeight = FontWeight.Bold, fontSize = 32.sp)
                    GradientButton(text = "Redeem", onClick = onRedeemClick)
                }
            }
        }
    }
}          
