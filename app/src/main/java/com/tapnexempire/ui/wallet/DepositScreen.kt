package com.tapnexempire.ui.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tapnexempire.ui.theme.Gold
import com.tapnexempire.ui.theme.LightCream
import com.tapnexempire.ui.theme.CharcoalBlack

@Composable
fun DepositScreen(
    navController: NavController,
    onDeposit: (Int) -> Unit
) {
    var depositAmount by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightCream)
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Deposit Coins",
                fontSize = 24.sp,
                color = CharcoalBlack,
                modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
            )

            OutlinedTextField(
                value = depositAmount,
                onValueChange = { depositAmount = it },
                label = { Text("Enter amount") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val amount = depositAmount.toIntOrNull() ?: 0
                    if (amount > 0) {
                        onDeposit(amount)
                        navController.popBackStack()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Gold),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(50.dp)
            ) {
                Text(
                    text = "Deposit",
                    color = CharcoalBlack,
                    fontSize = 18.sp
                )
            }
        }
    }
}
