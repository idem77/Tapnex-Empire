package com.tapnexempire.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background

@Composable
fun EmpireWalletCard(

    depositCoins: Long,

    withdrawableCoins: Long,

    bonusCoins: Long
) {

    Card(

        modifier = Modifier
            .fillMaxWidth(),

        shape = RoundedCornerShape(28.dp),

        colors = CardDefaults.cardColors(
            containerColor =
                Color.Transparent
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        )
    ) {

        Column(

            modifier = Modifier

                .background(

                    brush = Brush.verticalGradient(

                        colors = listOf(

                            Color(0xFF23262F),

                            Color(0xFF16181D)
                        )
                    )
                )

                .padding(22.dp)
        ) {

            Text(

                text = "👑 Empire Wallet",

                color = Color.White,

                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(

                text = "💰 Deposit Coins: $depositCoins",

                color = Color(0xFFFFD54F),

                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(

                text =
                    "🏆 Withdrawable: $withdrawableCoins",

                color = Color(0xFF81C784),

                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(

                text = "🎁 Bonus Coins: $bonusCoins",

                color = Color(0xFF64B5F6),

                fontSize = 18.sp
            )
        }
    }
}
