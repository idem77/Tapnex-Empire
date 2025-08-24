package com.tapnexempire.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameTile(name: String, onClick: () -> Unit) {
        Box(
                    modifier = Modifier
                                .fillMaxWidth()
                                            .padding(horizontal = 16.dp, vertical = 8.dp)
                                                        .background(Color.DarkGray, shape = RoundedCornerShape(16.dp))
                                                                    .clickable { onClick() }
                                                                                .padding(20.dp)
        ) {
                    Text(
                                    text = name,
                                                fontSize = 18.sp,
                                                            color = Color.White
                    )
        }
}
                    )
        }
        )
}
