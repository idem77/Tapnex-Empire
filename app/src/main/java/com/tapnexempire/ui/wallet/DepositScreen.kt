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
import androidx.hilt.navigation.compose.hiltViewModel 
import com.tapnexempire.R 
import com.tapnexempire.components.AppButton 
import com.tapnexempire.viewmodel.DepositViewModel 
import com.tapnexempire.ui.theme.EmpireGold 
import com.tapnexempire.ui.theme.EmpireWhite

@Composable fun DepositScreen(
userId: String,

onBack: () -> Unit,

viewModel: DepositViewModel = hiltViewModel()
) {
var amount by remember {
    mutableStateOf("")
}

var upiRef by remember {
    mutableStateOf("")
}

var loading by remember {
    mutableStateOf(false)
}

val rupees =
    amount.toIntOrNull() ?: 0

val coins =
    rupees * 10

Box(
    modifier = Modifier.fillMaxSize()
) {

    Image(
        painter = painterResource(id = R.drawable.wallet_bg),
        contentDescription = null,
        modifier = Modifier.matchParentSize(),
        contentScale = ContentScale.Crop
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
            text = "💰 Deposit Coins",
            style = MaterialTheme.typography.headlineLarge,
            color = EmpireWhite
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Secure • Fast • Premium",
            color = EmpireGold,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "Enter Amount",
                    color = EmpireGold
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                OutlinedTextField(

                    value = amount,

                    onValueChange = {
                        amount = it
                    },

                    label = {
                        Text("Amount ₹")
                    },

                    keyboardOptions =
                        KeyboardOptions(
                            keyboardType =
                                KeyboardType.Number
                        ),

                    modifier = Modifier.fillMaxWidth(),

                    singleLine = true
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),

                        horizontalAlignment =
                            Alignment.CenterHorizontally
                    ) {

                        Text("🎁 You Will Receive")

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            text = "$coins Coins",
                            color = EmpireGold,
                            fontSize = 28.sp
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text("💳 Pay To")

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            text = "YOUR_UPI_ID_HERE"
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                OutlinedTextField(

                    value = upiRef,

                    onValueChange = {
                        upiRef = it
                    },

                    label = {
                        Text("UPI Reference Number")
                    },

                    modifier = Modifier.fillMaxWidth(),

                    singleLine = true
                )
            }
        }

        Spacer(
            modifier = Modifier.height(25.dp)
        )

        AppButton(

            text =
                if (loading)
                    "Submitting..."
                else
                    "📤 Submit Deposit",

            enabled =
                rupees > 0 &&
                upiRef.isNotBlank() &&
                !loading,

            onClick = {

                loading = true

                viewModel.submitDeposit(

                    userId = userId,

                    amountRupees = rupees.toLong(),

                    amountCoins = coins.toLong(),

                    upiRef = upiRef

                ) { success, message ->

                    loading = false

                    if (success) {

                        amount = ""
                        upiRef = ""
                    }
                }
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
