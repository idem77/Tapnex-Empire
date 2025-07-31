package com.yourpackage.tapnexempire.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yourpackage.tapnexempire.components.CoinCard
import com.yourpackage.tapnexempire.components.GameTile

@Composable
fun HomeScreen(navController: NavController) {
        Scaffold(
                    topBar = {
                                    TopAppBar(
                                                        title = { Text("Tapnex Empire") },
                                                                        colors = TopAppBarDefaults.topAppBarColors(
                                                                                                containerColor = MaterialTheme.colorScheme.primary
                                                                        )
                                    )
                    }
        ) { paddingValues ->
                Column(
                                modifier = Modifier
                                                .padding(paddingValues)
                                                                .padding(16.dp)
                ) {
                                CoinCard(coinAmount = 1000)

                                            Spacer(modifier = Modifier.height(16.dp))

                                                        Text(
                                                                            text = "Games",
                                                                                            style = MaterialTheme.typography.titleLarge
                                                        )

                                                                    Spacer(modifier = Modifier.height(8.dp))

                                                                                GameTile(gameName = "Ludo", onClick = {
                                                                                                    navController.navigate("game")
                                                                                })

                                                                                            Spacer(modifier = Modifier.height(8.dp))

                                                                                                        GameTile(gameName = "Task & Redeem", onClick = {
                                                                                                                            navController.navigate("task")
                                                                                                        })
                }
        }
}
                                                                                                        })
                                                                                })
                                                        )
                }
                )}
                                                                        )
                                    )
                    }
        )
}