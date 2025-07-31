package com.example.tapnexempire.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.platform.LocalUriHandler

@Composable
fun TasksScreen() {
        val uriHandler: UriHandler = LocalUriHandler.current

            Column(
                        modifier = Modifier
                                    .fillMaxSize()
                                                .padding(16.dp)
            ) {
                        Text(
                                        text = "Daily Tasks",
                                                    style = MaterialTheme.typography.headlineSmall,
                                                                modifier = Modifier.padding(bottom = 16.dp)
                        )

                                // Example task
                                        Text(
                                                        text = "✅ Watch a video to earn 10 coins",
                                                                    fontSize = 16.sp,
                                                                                modifier = Modifier.padding(bottom = 12.dp)
                                        )

                                                // Example clickable YouTube link
                                                        ClickableText(
                                                                        text = AnnotatedString("📺 Visit our YouTube Channel"),
                                                                                    onClick = {
                                                                                                        uriHandler.openUri("https://www.youtube.com/@YourChannelName")
                                                                                    },
                                                                                                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Blue)
                                                        )
            }
}
                                                                                    }
                                                        )
                                        )
                        )
            }
            )
}