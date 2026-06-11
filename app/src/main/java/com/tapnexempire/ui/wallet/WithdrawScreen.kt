package com.tapnexempire.ui.wallet

import androidx.compose.foundation.Image import androidx.compose.foundation.layout.* import androidx.compose.foundation.rememberScrollState import androidx.compose.foundation.text.KeyboardOptions import androidx.compose.foundation.verticalScroll import androidx.compose.material3.* import androidx.compose.runtime.* import androidx.compose.ui.Alignment import androidx.compose.ui.Modifier import androidx.compose.ui.layout.ContentScale import androidx.compose.ui.res.painterResource import androidx.compose.ui.text.input.KeyboardType import androidx.compose.ui.unit.dp import androidx.compose.ui.unit.sp import com.tapnexempire.R import com.tapnexempire.components.AppButton import com.tapnexempire.ui.theme.EmpireGold import com.tapnexempire.ui.theme.EmpireWhite
@Composable fun WithdrawScreen(
withdrawableCoins: Long,

onBack: () -> Unit,

onWithdraw: (
    coins: Long,
    redeemCode: String
) -> Unit
) {
var coinInput by remember {
    mutableStateOf("")
}

var redeemCode by remember {
    mutableStateOf("")
}

val coins =
    coinInput.toLongOrNull() ?: 0L

val rupees =
    coins / 10

val isValid =
    coins in 100..2000 &&
    redeemCode.isNotBlank()

Box(
    modifier = Modifier.fillMaxSize()
) {

    Image(
        painter = painterResource(id = R.drawable.wallet_bg),
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

        Text(
            text = "💸 Withdraw Coins",
            style = MaterialTheme.typography.headlineLarge,
            color = EmpireWhite
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text =
                        "Available Coins: $withdrawableCoins",
                    color = EmpireGold
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                OutlinedTextField(

                    value = coinInput,

                    onValueChange = {
                        coinInput = it
                    },

                    label = {
                        Text("Withdraw Coins")
                    },

                    keyboardOptions =
                        KeyboardOptions(
                            keyboardType =
                                KeyboardType.Number
                        ),

                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),

                        horizontalAlignment =
                            Alignment.CenterHorizontally
                    ) {

                        Text("🎁 You Will Receive")

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            text = "₹$rupees",
                            color = EmpireGold,
                            fontSize = 28.sp
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                OutlinedTextField(

                    value = redeemCode,

                    onValueChange = {
                        redeemCode = it
                    },

                    label = {
                        Text("Redeem Code / Gift Card")
                    },

                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text =
                        "Minimum: 100 Coins (₹10)\nMaximum: 2000 Coins (₹200)",
                    color = EmpireWhite
                )
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        AppButton(

            text = "🚀 Submit Withdraw",

            enabled = isValid,

            onClick = {

                onWithdraw(
                    coins,
                    redeemCode
                )
            }
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Back")
        }

        Spacer(
            modifier = Modifier.height(40.dp)
        )
    }
}
}
