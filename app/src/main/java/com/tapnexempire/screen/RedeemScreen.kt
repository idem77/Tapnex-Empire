package com.tapnexempire.screens.redeem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import com.tapnexempire.ui.theme.NeonBlue

data class RedeemOption(
    val coins: Int,
    val reward: String
)

@Composable
fun RedeemScreen() {
    val redeemOptions = listOf(
        RedeemOption(1000, "₹10"),
        RedeemOption(3000, "₹30"),
        RedeemOption(5000, "₹50"),
        RedeemOption(10000, "₹100")
    )

    var selectedOption by remember { mutableStateOf<RedeemOption?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Redeem Coins",
                color = NeonBlue,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(redeemOptions) { option ->
                    RedeemCard(
                        option = option,
                        isSelected = selectedOption == option,
                        onClick = { selectedOption = option }
                    )
                }
            }

            Button(
                onClick = {
                    if (selectedOption != null) showDialog = true
                },
                colors = ButtonDefaults.buttonColors(containerColor = NeonBlue),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                enabled = selectedOption != null
            ) {
                Text("Redeem Now", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }

        // Confirmation Dialog
        if (showDialog && selectedOption != null) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Text("Confirm Redeem", color = NeonBlue, fontWeight = FontWeight.Bold)
                },
                text = {
                    Text(
                        "Are you sure you want to redeem ${selectedOption!!.coins} coins for ${selectedOption!!.reward}?",
                        color = Color.White
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            // ✅ Show snackbar when redeemed
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "🎉 Successfully redeemed ${selectedOption!!.reward}",
                                    duration = SnackbarDuration.Short
                                )
                            }
                            showDialog = false
                            selectedOption = null
                        }
                    ) {
                        Text("Yes", color = NeonBlue, fontWeight = FontWeight.Bold)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancel", color = Color.Gray)
                    }
                },
                containerColor = Color.DarkGray
            )
        }
    }
}

@Composable
fun RedeemCard(option: RedeemOption, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) NeonBlue.copy(alpha = 0.3f) else Color.DarkGray
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "${option.reward} Reward",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Requires ${option.coins} Coins",
                    color = Color.LightGray,
                    fontSize = 14.sp
                )
            }
            if (isSelected) {
                Text("Selected", color = NeonBlue, fontWeight = FontWeight.Bold)
            }
        }
    }
}
