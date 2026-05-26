package com.tapnexempire.ui.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DepositRequestScreen() {

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0E1015))
            .padding(18.dp)
            .verticalScroll(
                rememberScrollState()
            ),

        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(

            text = "💰 Deposit Requests",

            color = Color.White
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        DepositRequestCard(

            userName = "EmpirePlayer",

            amount = "₹500",

            upiRef = "UPI847392"
        )

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        DepositRequestCard(

            userName = "ShadowKing",

            amount = "₹1000",

            upiRef = "UPI274829"
        )
    }
}

@Composable
fun DepositRequestCard(

    userName: String,

    amount: String,

    upiRef: String
) {

    Card(

        modifier = Modifier
            .fillMaxWidth(),

        colors = CardDefaults.cardColors(

            containerColor =
                Color(0xCC1A1C22)
        )
    ) {

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {

            Text(

                text = "👤 $userName",

                color = Color.White
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(

                text = "💰 Amount: $amount",

                color = Color(0xFFFFD54F)
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(

                text = "🧾 Ref ID: $upiRef",

                color = Color.LightGray
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween
            ) {

                Button(

                    onClick = {

                    },

                    colors = ButtonDefaults.buttonColors(

                        containerColor =
                            Color(0xFF2E7D32)
                    )
                ) {

                    Text(
                        text = "Approve"
                    )
                }

                Button(

                    onClick = {

                    },

                    colors = ButtonDefaults.buttonColors(

                        containerColor =
                            Color(0xFFC62828)
                    )
                ) {

                    Text(
                        text = "Reject"
                    )
                }
            }
        }
    }
}
