package com.tapnexempire.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.viewmodel.AdminViewModel

@Composable
fun UserManagementScreen(
    adminViewModel: AdminViewModel = hiltViewModel()
) {

    var users by remember {
        mutableStateOf(listOf<Map<String, Any>>())
    }

    LaunchedEffect(Unit) {
        adminViewModel.listenUsers {
            users = it
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("👥 Live Users", color = Color.White)

        LazyColumn {

            items(users) { user ->

                val userId = user["userId"]?.toString() ?: ""
                val name = user["name"]?.toString() ?: "Unknown"
                val coins = user["coins"]?.toString() ?: "0"

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(Color(0xFF1A1C22))
                ) {

                    Column(Modifier.padding(12.dp)) {

                        Text("👤 $name", color = Color.White)
                        Text("💰 Coins: $coins", color = Color.Yellow)

                        Row {

    Button(
        onClick = {
            adminViewModel.banUser(userId)
        }
    ) {
        Text("Ban")
    }

    Spacer(Modifier.width(8.dp))

    Button(
        onClick = {
            adminViewModel.unbanUser(userId)
        }
    ) {
        Text("Unban")
    }
                        }
                    }
                }
            }
        }
    }
}
