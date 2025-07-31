package com.example.tapnexempire.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tapnexempire.components.PrimaryButton

@Composable
fun RedeemScreen() {
            var coins by remember { mutableStateOf(0) }
                var showSuccess by remember { mutableStateOf(false) }

                    Column(
                                modifier = Modifier
                                            .fillMaxSize()
                                                        .padding(16.dp)
                    ) {
                                Text(
                                                    text = "Redeem Coins",
                                                                style = MaterialTheme.typography.headlineSmall,
                                                                            modifier = Modifier.padding(bottom = 16.dp)
                                )

                                        Text(
                                                            text = "Enter coins to redeem:",
                                                                        fontSize = 16.sp,
                                                                                    modifier = Modifier.padding(bottom = 8.dp)
                                        )

                                                OutlinedTextField(
                                                                    value = coins.toString(),
                                                                                onValueChange = {
                                                                                                        coins = it.toIntOrNull() ?: 0
                                                                                                                        showSuccess = false
                                                                                },
                                                                                            modifier = Modifier.fillMaxWidth()
                                                )

                                                        Spacer(modifier = Modifier.height(16.dp))

                                                                PrimaryButton(text = "Redeem") {
                                                                                    if (coins > 0) {
                                                                                                        showSuccess = true
                                                                                    }
                                                                }

                                                                        if (showSuccess) {
                                                                                            Spacer(modifier = Modifier.height(16.dp))
                                                                                                        Text(
                                                                                                                                text = "✅ Successfully requested ₹${coins / 10}.",
                                                                                                                                                fontSize = 16.sp,
                                                                                                                                                                color = MaterialTheme.colorScheme.primary
                                                                                                        )
                                                                        }
                    }
}
                                                                                                        )
                                                                        }
                                                                                    }
                                                                }
                                                                                }
                                                )
                                        )
                                )
                    }
                    )
}