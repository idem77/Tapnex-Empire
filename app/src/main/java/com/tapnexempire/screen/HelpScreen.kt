package com.example.tapnexempire.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HelpScreen() {
        Column(
                    modifier = Modifier
                                .fillMaxSize()
                                            .padding(24.dp),
                                                    verticalArrangement = Arrangement.Top
        ) {
                    Text(
                                    text = "Help & Support",
                                                style = MaterialTheme.typography.headlineSmall,
                                                            modifier = Modifier.padding(bottom = 16.dp)
                    )

                            Text(
                                            text = "If you have any questions or need help, feel free to contact our support team.",
                                                        fontSize = 16.sp,
                                                                    modifier = Modifier.padding(bottom = 16.dp)
                            )

                                    Text(
                                                    text = "Email: support@tapnex.com",
                                                                fontSize = 16.sp,
                                                                            modifier = Modifier.padding(bottom = 8.dp)
                                    )

                                            Text(
                                                            text = "FAQs: Coming Soon!",
                                                                        fontSize = 16.sp,
                                                                                    modifier = Modifier.padding(bottom = 8.dp)
                                            )
        }
}
                                            )
                                    )
                            )
                    )
        }
        )
}