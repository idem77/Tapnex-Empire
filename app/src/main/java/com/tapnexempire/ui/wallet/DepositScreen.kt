package com.tapnexempire.ui.wallet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.R
import com.tapnexempire.components.AppButton
import com.tapnexempire.ui.theme.EmpireGold
import com.tapnexempire.ui.theme.EmpireWhite

@Composable
fun DepositScreen(

    onBack: () -> Unit,

    onProceed: (Int) -> Unit
) {

    var amount by remember {

        mutableStateOf("")
    }

    val rupees =
        amount.toIntOrNull() ?: 0

    val coins = rupees * 10

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // 👑 WALLET BACKGROUND
        Image(
            painter =
                painterResource(id = R.drawable.wallet_bg),

            contentDescription = null,

            modifier = Modifier.fillMaxSize(),

            contentScale = ContentScale.FillBounds
        )

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 50.dp)
                .verticalScroll(rememberScrollState()),

            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            // 👑 TITLE
            Text(

                text = "💰 Deposit Coins",

                style =
                    MaterialTheme.typography.headlineLarge,

                color = EmpireWhite
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(

                text =
                    "Secure • Fast • Premium",

                color = EmpireGold,

                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(35.dp))

            // 👑 MAIN CARD
            Card(

                modifier = Modifier.fillMaxWidth(),

                colors = CardDefaults.cardColors(
                    containerColor =
                        MaterialTheme.colorScheme.surface
                ),

                elevation =
                    CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
            ) {

                Column(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(22.dp)
                ) {

                    Text(

                        text = "Enter Amount",

                        color = EmpireGold,

                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    OutlinedTextField(

                        value = amount,

                        onValueChange = {

                            amount = it
                        },

                        label = {

                            Text("Amount (₹)")
                        },

                        keyboardOptions =
                            KeyboardOptions(

                                keyboardType =
                                    KeyboardType.Number
                            ),

                        modifier = Modifier.fillMaxWidth(),

                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(22.dp))

                    Card(

                        modifier = Modifier.fillMaxWidth(),

                        colors = CardDefaults.cardColors(
                            containerColor =
                                MaterialTheme.colorScheme.background
                        )
                    ) {

                        Column(

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp),

                            horizontalAlignment =
                                Alignment.CenterHorizontally
                        ) {

                            Text(

                                text =
                                    "🎁 You Will Receive",

                                color = EmpireWhite
                            )

                            Spacer(
                                modifier =
                                    Modifier.height(10.dp)
                            )

                            Text(

                                text = "$coins Coins",

                                color = EmpireGold,

                                fontSize = 28.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // 👑 PAY BUTTON
            AppButton(

                text = "🚀 Proceed To Pay",

                enabled = rupees > 0,

                onClick = {

                    onProceed(coins)
                }
            )

            Spacer(modifier = Modifier.height(14.dp))

            // 👑 BACK BUTTON
            OutlinedButton(

                onClick = onBack,

                modifier = Modifier.fillMaxWidth()
            ) {

                Text("Back")
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}
