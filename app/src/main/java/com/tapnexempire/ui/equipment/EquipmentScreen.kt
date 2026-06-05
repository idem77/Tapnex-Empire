package com.tapnexempire.ui.equipment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.R

@Composable
fun EquipmentScreen() {

Box(
    modifier = Modifier.fillMaxSize()
) {

    // 👑 LOCKED BG
    Image(
        painter = painterResource(
            id = R.drawable.equipment_locked_bg
        ),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )

    // 👑 BLACK OVERLAY
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color.Black.copy(alpha = 0.7f)
            )
    )

    // 👑 CENTER CONTENT
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment =
            Alignment.CenterHorizontally,
        verticalArrangement =
            Arrangement.Center
    ) {

        Text(
            text = "🔒",
            fontSize = 90.sp
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "LOCKED",
            color = Color.White,
            fontSize = 38.sp
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text(
            text = "Become The True Immortal",
            color = Color.Yellow,
            fontSize = 22.sp
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text =
            "Unlock All Bundles,\nRunes & Magic Effects",
            color = Color.White,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "Required Deposit",
            color = Color.LightGray,
            fontSize = 18.sp
        )

        Text(
            text = "1000 COINS",
            color = Color.Yellow,
            fontSize = 32.sp
        )
   }
  } 
}
