package com.tapnexempire.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmpireBundleCard(

    title: String,

    coins: Int,

    price: String,

    onBuy: () -> Unit
) {

    Card(

        modifier = Modifier
            .fillMaxWidth(),

        shape = RoundedCornerShape(28.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {

        Column(

            modifier = Modifier

                .background(

                    brush = Brush.verticalGradient(

                        colors = listOf(

                            Color(0xFF2B1D00),

                            Color(0xFF16181D)
                        )
                    )
                )

                .padding(22.dp)
        ) {

            Text(

                text = title,

                color = Color.White,

                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(

                text = "🪙 $coins Coins",

                color = Color(0xFFFFD54F),

                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(

                text = price,

                color = Color.White,

                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(18.dp))

            Button(

                onClick = onBuy,

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(18.dp),

                colors = ButtonDefaults.buttonColors(

                    containerColor =
                        Color(0xFFFFC107)
                )
            ) {

                Text(
                    text = "Buy Now",
                    color = Color.Black
                )
            }
        }
    }
}
