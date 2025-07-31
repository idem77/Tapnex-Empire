package com.example.tapnexempire.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tapnexempire.components.PrimaryButton

@Composable
fun ProfileScreen() {
        val userName = "Kartik"
            val userEmail = "kartik@example.com"

                Column(
                            modifier = Modifier
                                        .fillMaxSize()
                                                    .padding(24.dp),
                                                            verticalArrangement = Arrangement.Center
                ) {
                            Text(
                                            text = "Profile",
                                                        style = MaterialTheme.typography.headlineSmall,
                                                                    modifier = Modifier.padding(bottom = 24.dp)
                            )

                                    Text(
                                                    text = "Name: $userName",
                                                                fontSize = 18.sp,
                                                                            fontWeight = FontWeight.Medium,
                                                                                        modifier = Modifier.padding(bottom = 8.dp)
                                    )

                                            Text(
                                                            text = "Email: $userEmail",
                                                                        fontSize = 18.sp,
                                                                                    fontWeight = FontWeight.Medium,
                                                                                                modifier = Modifier.padding(bottom = 24.dp)
                                            )

                                                    PrimaryButton(text = "Logout") {
                                                                    // TODO: Implement logout logic later
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