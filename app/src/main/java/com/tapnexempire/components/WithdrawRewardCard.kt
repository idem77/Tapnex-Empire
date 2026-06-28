package com.tapnexempire.ui.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tapnexempire.data.model.WithdrawHistoryModel

@Composable
fun WithdrawRewardCard(

    withdraw: WithdrawHistoryModel

) {

    val statusColor = when (withdraw.status) {

        "approved" -> Color(0xFF00C853)

        "pending" -> Color(0xFFFFC107)

        "rejected" -> Color(0xFFD50000)

        else -> Color.Gray
    }

    Card(

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(20.dp),

        colors = CardDefaults.cardColors(

            containerColor = Color(0xCC182338)

        )

    ) {

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)

        ) {

            Text(

                text = "🎁 ${withdraw.rewardType}",

                style = MaterialTheme.typography.titleLarge,

                color = Color.White

            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(

                text = "₹${withdraw.amountRupees}",

                style = MaterialTheme.typography.headlineMedium,

                color = Color(0xFFFFD54F)

            )

            Spacer(modifier = Modifier.height(12.dp))

            Box(

                modifier = Modifier
                    .background(
                        statusColor,
                        RoundedCornerShape(50.dp)
                    )
                    .padding(
                        horizontal = 14.dp,
                        vertical = 6.dp
                    )

            ) {

                Text(

                    text = withdraw.status.uppercase(),

                    color = Color.White

                )

            }

            Spacer(modifier = Modifier.height(18.dp))

            when (withdraw.status) {

                "approved" -> {

                    Text(
                        "Redeem Code",
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(

                        text = withdraw.redeemCode,

                        color = Color(0xFF00E5FF),

                        style = MaterialTheme.typography.titleMedium

                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(

                        onClick = {

                            // Copy code later

                        }

                    ) {

                        Text("📋 Copy Code")

                    }
                }

                "pending" -> {

                    Text(

                        text = "⏳ Waiting For Admin",

                        color = Color.Yellow

                    )

                }

                "rejected" -> {

                    Text(

                        text = "❌ Please submit a new withdraw request.",

                        color = Color.Red

                    )

                }

            }

        }

    }

}
