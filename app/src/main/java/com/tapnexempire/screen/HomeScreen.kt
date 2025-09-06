package com.tapnexempire.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import com.tapnexempire.components.GradientButton
import com.tapnexempire.ui.theme.RoyalTeal
import com.tapnexempire.ui.theme.VibrantCoral
import com.tapnexempire.ui.theme.SoftCream
import com.tapnexempire.ui.theme.White
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.border
import androidx.compose.material3.IconDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.MaterialTheme

@Composable
fun HomeScreen(
    onWalletClick: () -> Unit = {},
    onGameClick: () -> Unit = {}
) {
    Surface(modifier = Modifier.fillMaxSize(), color = SoftCream) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Top bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Welcome, In Tapnex empire!", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(text = "Play & earn coins", fontSize = 14.sp, color = com.tapnexempire.ui.theme.WarmGrey)
                }
                IconButton(onClick = onWalletClick) {
                    Icon(Icons.Default.AccountCircle, contentDescription = "Profile", tint = RoyalTeal, modifier = Modifier.size(36.dp))
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                item {
                    // Coin card
                    val gradient = Brush.horizontalGradient(listOf(RoyalTeal, VibrantCoral))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .shadow(8.dp, RoundedCornerShape(20.dp))
                            .background(brush = gradient, shape = RoundedCornerShape(20.dp))
                            .padding(16.dp)
                    ) {
                        Column(modifier = Modifier.align(Alignment.CenterStart)) {
                            Text("Coins", color = White, fontWeight = FontWeight.Medium)
                            Spacer(modifier = Modifier.height(6.dp))
                            Text("1,540", color = White, fontWeight = FontWeight.Bold, fontSize = 28.sp)
                            Spacer(modifier = Modifier.height(10.dp))
                            GradientButton(text = "Redeem", modifier = Modifier.fillMaxWidth(0.5f)) { /*redeem*/ }
                        }
                    }
                    Spacer(modifier = Modifier.height(18.dp))
                }

                item {
                    // Featured game tile (Ludo)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(6.dp, RoundedCornerShape(16.dp))
                            .background(Brush.horizontalGradient(listOf(RoyalTeal, VibrantCoral)), RoundedCornerShape(16.dp))
                            .padding(16.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            // placeholder square for game image
                            Box(
                                modifier = Modifier
                                    .size(72.dp)
                                    .background(White, RoundedCornerShape(12.dp))
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text("Ludo", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = White)
                                Text("Play with friends", color = White.copy(alpha = 0.9f))
                                Spacer(modifier = Modifier.height(12.dp))
                                GradientButton(text = "Play", modifier = Modifier.width(120.dp)) {
                                    onGameClick()
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(18.dp))
                }

                item {
                    // Quick actions / cards row (example)
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(96.dp)
                                .shadow(6.dp, RoundedCornerShape(14.dp))
                                .background(White, RoundedCornerShape(14.dp))
                                .padding(12.dp)
                        ) {
                            Column { Text("Daily", fontWeight = FontWeight.Bold); Text("Login reward", color = com.tapnexempire.ui.theme.WarmGrey) }
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(96.dp)
                                .shadow(6.dp, RoundedCornerShape(14.dp))
                                .background(White, RoundedCornerShape(14.dp))
                                .padding(12.dp)
                        ) {
                            Column { Text("Tournaments", fontWeight = FontWeight.Bold); Text("Join now", color = com.tapnexempire.ui.theme.WarmGrey) }
                        }
                    }
                    Spacer(modifier = Modifier.height(36.dp))
                }
            }
        }
    }
}
