package com.tapnexempire.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tapnexempire.R

@Composable
fun HomeScreen() {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // 🔥 BACKGROUND
        Image(
            painter = painterResource(id = R.drawable.home_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // 🔥 CONTENT AREA
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 70.dp) // space for bottom nav
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            // 👑 EMPIRE TITLE (temporary, can remove later)
            Text(
                text = "Tapnex Empire",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 💰 COIN BALANCE CARD (placeholder)
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(120.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Balance: 0 Coins",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 🎯 SLIDE BANNER AREA (Future HorizontalPager here)
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(150.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Text("Upcoming Tournaments / Offers")
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}
