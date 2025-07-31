package com.tapnexempire.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CoinCard(coins: Int) {
        Box(
                    modifier = Modifier
                                .fillMaxWidth()
                                            .padding(16.dp)
                                                        .height(120.dp)
                                                                    .background(Color(0xFF1F1F1F), RoundedCornerShape(20.dp)),
                                                                            contentAlignment = Alignment.Center
        ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                                        text = "$coins Coins",
                                                                        style = MaterialTheme.typography.headlineMedium,
                                                                                        color = Color.Yellow,
                                                                                                        fontSize = 24.sp,
                                                                                                                        fontWeight = FontWeight.Bold
                                    )
                                                Text(
                                                                    text = "Total Balance",
                                                                                    color = Color.White.copy(alpha = 0.6f)
                                                )
                    }
        }
}
                                                )
                                    )
                    }
        }
        )
}