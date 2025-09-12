package com.tapnexempire.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(navController: androidx.navigation.NavController) {
    Scaffold(topBar = { CenterAlignedTopAppBar(title = { Text("Profile") }) }) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            Text("User Name")
            Spacer(Modifier.height(8.dp))
            Text("Phone: +91-XXXXXXXXXX")
            Spacer(Modifier.height(16.dp))
            Button(onClick = { /* logout */ }) { Text("Logout") }
        }
    }
}
