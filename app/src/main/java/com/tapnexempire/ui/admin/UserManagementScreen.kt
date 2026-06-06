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
fun UserManagementScreen(
    adminViewModel: AdminViewModel = hiltViewModel()
) {

    val users = adminViewModel.usersLive

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("👥 Live Users", color = Color.White)

        Spacer(Modifier.height(10.dp))

        users.forEach { user ->

            val userId = user["userId"]?.toString() ?: ""
            val name = user["name"]?.toString() ?: "Unknown"
            val coins = user["coins"]?.toString() ?: "0"

            Card(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                colors = CardDefaults.cardColors(Color(0xFF1A1C22))
            ) {

                Column(Modifier.padding(12.dp)) {

                    Text("👤 $name", color = Color.White)
                    Text("💰 Coins: $coins", color = Color.Yellow)

                    Row {

                        Button(onClick = {
                            adminViewModel.addCoins(userId, 100)
                        }) {
                            Text("+100 Coins")
                        }

                        Spacer(Modifier.width(8.dp))

                        Button(onClick = {
                            adminViewModel.ban(userId)
                        }) {
                            Text("Ban")
                        }
                    }
                }
            }
        }
    }
}
