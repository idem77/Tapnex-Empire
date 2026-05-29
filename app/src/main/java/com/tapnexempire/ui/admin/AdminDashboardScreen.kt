package com.tapnexempire.ui.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AdminDashboardScreen() {

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0E1015))
            .padding(18.dp)
            .verticalScroll(
                rememberScrollState()
            ),

        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {

        Spacer(
            modifier = Modifier.height(25.dp)
        )

        Text(

            text = "👑 Empire Admin Panel",

            style =
                MaterialTheme.typography.headlineMedium,

            color = Color.White
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Text(

            text =
                "Control The Entire Empire 😏🔥",

            color = Color(0xFFFFD54F),

            fontSize = 16.sp
        )

        Spacer(
            modifier = Modifier.height(28.dp)
        )

        // 👑 TOTAL USERS
        AdminCard(

            title = "👥 Total Users",

            value = "1,284"
        )

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        // 💰 TOTAL DEPOSITS
        AdminCard(

            title = "💰 Total Deposits",

            value = "₹84,520"
        )

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        // 🏆 TOURNAMENTS
        AdminCard(

            title = "🏆 Active Tournaments",

            value = "12"
        )

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        // 💎 BUNDLES
        AdminCard(

            title = "💎 Active Bundles",

            value = "8"
        )

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        // 📤 WITHDRAW REQUESTS
        AdminCard(

            title = "📤 Withdraw Requests",

            value = "24 Pending"
        )

        Spacer(
            modifier = Modifier.height(30.dp)
        )
    }
}

@Composable
fun AdminCard(

    title: String,

    value: String
) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp),

        colors = CardDefaults.cardColors(

            containerColor =
                Color(0xCC1A1C22)
        )
    ) {

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp),

            verticalArrangement =
                Arrangement.Center
        ) {

            Text(

                text = title,

                style =
                    MaterialTheme.typography.titleLarge,

                color = Color.White
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(

                text = value,

                color = Color(0xFFFFD54F),

                fontSize = 20.sp
            )
        }
    }
}
