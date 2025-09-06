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
import androidx.compose.ui.Alignment
import com.tapnexempire.ui.theme.RoyalTeal
import com.tapnexempire.ui.theme.VibrantCoral
import com.tapnexempire.ui.theme.SoftCream
import com.tapnexempire.ui.theme.White
import com.tapnexempire.components.GradientButton

@Composable
fun GameScreen(
    onPlayClick: () -> Unit = {}
) {
    Surface(modifier = Modifier.fillMaxSize(), color = SoftCream) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Games", fontWeight = FontWeight.Bold, fontSize = 22.sp)
            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .shadow(10.dp, RoundedCornerShape(20.dp))
                    .background(White, RoundedCornerShape(20.dp))
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(
                                Brush.horizontalGradient(listOf(RoyalTeal, VibrantCoral)),
                                RoundedCornerShape(16.dp)
                            )
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Ludo", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text("Entry: 200 Coins", color = VibrantCoral, fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(12.dp))
                        GradientButton(text = "Play & Earn") { onPlayClick() }
                    }
                }
            }
        }
    }
}
