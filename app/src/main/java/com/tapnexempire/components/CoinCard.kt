package com.tapnexempire.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CoinCard(
    coins: Int
) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .height(95.dp),

        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(

            containerColor =
                Color(0xFF1A1C22).copy(alpha = 0.92f)
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {

        Row(

            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),

            verticalAlignment = Alignment.CenterVertically,

            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            Column {

                Text(

                    text = "Empire Coins",

                    color = Color.LightGray,

                    fontSize = 15.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(

                    text = "🪙 $coins",

                    color = Color(0xFFFFD54F),

                    fontSize = 28.sp
                )
            }
        }
    }
}
