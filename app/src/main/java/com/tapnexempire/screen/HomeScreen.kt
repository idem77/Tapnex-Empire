package com.tapnexempire.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tapnexempire.ui.theme.NeonBlue
import com.tapnexempire.ui.theme.Gold

@Composable
fun HomeScreen(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                "Home Screen",
                fontSize = 28.sp,
                color = NeonBlue
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("help") },
                colors = ButtonDefaults.buttonColors(containerColor = Gold)
            ) {
                Text("Go to Help")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate("profile") },
                colors = ButtonDefaults.buttonColors(containerColor = Gold)
            ) {
                Text("Go to Profile")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate("wallet") },
                colors = ButtonDefaults.buttonColors(containerColor = Gold)
            ) {
                Text("Go to Wallet")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate("redeem") },
                colors = ButtonDefaults.buttonColors(containerColor = Gold)
            ) {
                Text("Go to Redeem")
            }
        }
    }
}
