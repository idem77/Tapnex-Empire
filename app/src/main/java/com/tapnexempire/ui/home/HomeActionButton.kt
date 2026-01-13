package com.tapnexempire.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.ui.theme.CharcoalBlack

@Composable
fun HomeActionButton(
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                color = CharcoalBlack
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = description,
                fontSize = 14.sp
            )
        }
    }
}
