package com.tapnexempire.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.BoxScope
import com.tapnexempire.R

@Composable
fun EmpireBackground(

    content: @Composable BoxScope.() -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // 🔥 BACKGROUND IMAGE
        Image(

            painter =
                painterResource(id = R.drawable.empire_bg),

            contentDescription = null,

            modifier = Modifier.fillMaxSize(),

            contentScale = ContentScale.Crop
        )

        // 🔥 DARK OVERLAY
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.Black.copy(alpha = 0.45f)
                )
        )

        // 🔥 SCREEN CONTENT
        content()
    }
}
