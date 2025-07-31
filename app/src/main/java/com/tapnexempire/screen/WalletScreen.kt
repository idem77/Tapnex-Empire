package com.example.tapnexempire.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tapnexempire.components.CoinCard

@Composable
fun WalletScreen() {
            Column(
                        modifier = Modifier
                                    .fillMaxSize()
                                                .padding(16.dp)
            ) {
                        Text(
                                            text = "My Wallet",
                                                        style = MaterialTheme.typography.headlineSmall,
                                                                    modifier = Modifier.padding(bottom = 16.dp)
                        )

                                CoinCard(
                                                    coins = 1250,
                                                                modifier = Modifier.fillMaxWidth()
                                )

                                        Spacer(modifier = Modifier.height(24.dp))

                                                Text(
                                                                    text = "📝 Note: Earn more coins by playing games or completing tasks!",
                                                                                fontSize = 14.sp,
                                                                                            color = MaterialTheme.colorScheme.secondary
                                                )
            }
}
                                                )
                                )
                        )
            }
            )
}