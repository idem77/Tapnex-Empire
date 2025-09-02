package com.tapnexempire.screen

import androidx.compose.foundation.clickable
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
fun HelpScreen(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Help & Support",
                fontSize = 28.sp,
                color = NeonBlue,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Text(
                text = "Contact Us",
                fontSize = 20.sp,
                color = Gold,
                modifier = Modifier
                    .clickable {
                        // Add navigation or action here
                    }
                    .padding(vertical = 8.dp)
            )

            Text(
                text = "FAQ",
                fontSize = 20.sp,
                color = Gold,
                modifier = Modifier
                    .clickable {
                        // Add navigation or action here
                    }
                    .padding(vertical = 8.dp)
            )

            Text(
                text = "Terms & Conditions",
                fontSize = 20.sp,
                color = Gold,
                modifier = Modifier
                    .clickable {
                        // Add navigation or action here
                    }
                    .padding(vertical = 8.dp)
            )
        }
    }
}
