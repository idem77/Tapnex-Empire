package com.tapnexempire.screens.help

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.ui.theme.NeonBlue

data class FaqItem(
    val question: String,
    val answer: String
)

@Composable
fun HelpScreen() {
    val faqList = listOf(
        FaqItem("How to earn coins?", "You can earn coins by playing games, completing tasks, offers and daily rewards."),
        FaqItem("How to redeem coins?", "Go to the Redeem screen, select your reward and redeem when you have enough coins."),
        FaqItem("Is Tapnex Empire free?", "Yes! The app is free to use. You just play and earn."),
        FaqItem("When will I receive my rewards?", "Rewards are usually processed within 24–48 hours."),
        FaqItem("How to contact support?", "Scroll down and tap 'Contact Support' to send us your query.")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text(
            text = "Help & Support",
            color = NeonBlue,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(faqList) { faq ->
                FaqCard(faq)
            }
        }

        Button(
            onClick = { /* Open support email or chat */ },
            colors = ButtonDefaults.buttonColors(containerColor = NeonBlue),
            modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
        ) {
            Text("Contact Support", color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun FaqCard(faq: FaqItem) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = faq.question,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .clickable { expanded = !expanded }
            )
            if (expanded) {
                Text(
                    text = faq.answer,
                    color = Color.LightGray,
                    fontSize = 14.sp
                )
            }
        }
    }
}
