package com.tapnexEmpire.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapnexEmpire.components.GameTile

@Composable
fun GamesScreen() {
            Scaffold(
                        topBar = {
                                            TopAppBar(
                                                                title = { Text("Play & Win") }
                                            )
                        }
            ) { paddingValues ->
                    LazyColumn(
                                    modifier = Modifier
                                                    .padding(paddingValues)
                                                                    .padding(16.dp)
                    ) {
                                    items(5) { index ->
                                                    GameTile(
                                                                            title = "Game $index",
                                                                                                description = "Exciting game number $index. Play now and win coins!"
                                                    )
                                                                    Spacer(modifier = Modifier.height(12.dp))
                                    }
                    }
            }
}
                                                    )}
                    }
                    )}
                                            )
                        }
            )
}