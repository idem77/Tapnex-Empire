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

    modifier = Modifier.fillMaxWidth(),

    shape = RoundedCornerShape(28.dp),

    colors = CardDefaults.cardColors(
        containerColor = Color.Transparent
    ),

    elevation = CardDefaults.cardElevation(
        defaultElevation = 18.dp
    )
) {

    Column(

        modifier = Modifier

            .background(

                brush = Brush.verticalGradient(

                    colors = listOf(

                        Color(0xFF081322),
                        Color(0xFF102A43),
                        Color(0xFF1565C0)
                    )
                )
            )

            .padding(24.dp)
    ) {

        Text(

            text = "👑 Empire Wallet",

            color = Color.White,

            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(

            text = "💰 Deposit Coins: $depositCoins",

            color = Color(0xFFFFE082),

            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(

            text = "🏆 Withdrawable: $withdrawableCoins",

            color = Color(0xFF80CBC4),

            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(

            text = "🎁 Bonus Coins: $bonusCoins",

            color = Color(0xFF81D4FA),

            fontSize = 18.sp
        )
    }
}

}
