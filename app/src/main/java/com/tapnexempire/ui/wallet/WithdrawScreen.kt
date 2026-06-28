package com.tapnexempire.ui.wallet

import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.*

import androidx.compose.foundation.Image import androidx.compose.foundation.layout.* import androidx.compose.foundation.rememberScrollState import androidx.compose.foundation.text.KeyboardOptions import androidx.compose.foundation.verticalScroll import androidx.compose.material3.* import androidx.compose.runtime.* import androidx.compose.ui.Alignment import androidx.compose.ui.Modifier import androidx.compose.ui.layout.ContentScale import androidx.compose.ui.res.painterResource import androidx.compose.ui.text.input.KeyboardType import androidx.compose.ui.unit.dp import androidx.compose.ui.unit.sp import com.tapnexempire.R import com.tapnexempire.components.AppButton import com.tapnexempire.ui.theme.EmpireGold import com.tapnexempire.ui.theme.EmpireWhite
@Composable fun WithdrawScreen(
withdrawableCoins: Long,
onBack: () -> Unit,

onWithdraw: (
    coins: Long,
    rewardType: String
) -> Unit
) {
var coinInput by remember {
    mutableStateOf("")
}

val rewardTypes = listOf(

    "Google Play",

    "Amazon",

    "Apple Gift Card",

    "Steam",

    "Netflix",

    "Flipkart"

)

var expanded by remember {

    mutableStateOf(false)

}

var rewardType by remember {

    mutableStateOf(rewardTypes.first())

}

val coins =
    coinInput.toLongOrNull() ?: 0L

val rupees =
    coins / 10

val isValid =
    coins in 100..2000 &&
    rewardType.isNotBlank()
    

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


                ExposedDropdownMenuBox(

    expanded = expanded,

    onExpandedChange = {

        expanded = !expanded

    }

) {

    OutlinedTextField(

        value = rewardType,

        onValueChange = {},

        readOnly = true,

        label = {

            Text("Reward Type")

        },

        trailingIcon = {

            ExposedDropdownMenuDefaults.TrailingIcon(
                expanded = expanded
            )

        },

        modifier = Modifier
            .menuAnchor()
            .fillMaxWidth()

    )

    ExposedDropdownMenu(

        expanded = expanded,

        onDismissRequest = {

            expanded = false

        }

    ) {

        rewardTypes.forEach { type ->

            DropdownMenuItem(

                text = {

                    Text(type)

                },

                onClick = {

                    rewardType = type

                    expanded = false

                }

            )

        }

    }

                }

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

        Spacer(modifier = Modifier.height(8.dp))

Text(
    text = "Selected: $rewardType",
    color = EmpireGold
)

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        AppButton(

            text = "🚀 Submit Withdraw",

            enabled = isValid,

            onClick = {

                onWithdraw(
                    coins,
                    rewardType
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
