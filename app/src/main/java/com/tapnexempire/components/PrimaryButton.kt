package com.tapnexempire.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(text: String, onClick: () -> Unit) {
        Button(
                    onClick = onClick,
                            modifier = Modifier
                                        .fillMaxWidth()
                                                    .padding(horizontal = 16.dp)
        ) {
                    Text(text = text)
        }
}
        }
        )
}
