package com.tapnexempire.screens.offers

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.ui.theme.NeonBlue

data class OfferItem(
    val title: String,
    val description: String,
    val reward: Int
)

@Composable
fun OffersScreen() {
    val offers = listOf(
        OfferItem("Install XYZ App", "Download and open XYZ app", 300),
        OfferItem("Survey on Gaming", "Complete 5 min survey", 150),
        OfferItem("Play ABC Game", "Reach Level 5 in ABC game", 500),
        OfferItem("Sign up to DEF Service", "Register with your email", 250),
        OfferItem("Watch Sponsored Video", "Watch full 30 sec video", 100)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text(
            text = "Special Offers",
            color = NeonBlue,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(offers) { offer ->
                OfferCard(offer)
            }
        }
    }
}

@Composable
fun OfferCard(offer: OfferItem) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable { /* handle offer click */ },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = offer.title,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = offer.description,
                color = Color.LightGray,
                fontSize = 14.sp
            )
            Text(
                text = "+${offer.reward} Coins",
                color = NeonBlue,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}
