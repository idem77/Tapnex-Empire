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
fun EventControlScreen() {

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
            modifier = Modifier.height(20.dp)
        )

        Text(

            text = "🎉 Event Control",

            color = Color.White
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        EventCard(

            title = "Face Reveal Event",

            reward = "Legendary Aura",

            status = "ACTIVE"
        )

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        EventCard(

            title = "Empire Festival",

            reward = "5000 Coins",

            status = "COMING SOON"
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Button(

            onClick = {

            },

            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),

            colors = ButtonDefaults.buttonColors(

                containerColor =
                    Color(0xFFFFD54F)
            )
        ) {

            Text(

                text = "✨ Create Event",

                color = Color.Black
            )
        }
    }
}

@Composable
fun EventCard(

    title: String,

    reward: String,

    status: String
) {

    Card(

        modifier = Modifier
            .fillMaxWidth(),

        colors = CardDefaults.cardColors(

            containerColor =
                Color(0xCC1A1C22)
        )
    ) {

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {

            Text(

                text = "👑 $title",

                color = Color.White
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(

                text = "🎁 Reward: $reward",

                color = Color(0xFFFFD54F)
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(

                text = "🔥 Status: $status",

                color = Color.LightGray
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween
            ) {

                Button(

                    onClick = {

                    },

                    colors = ButtonDefaults.buttonColors(

                        containerColor =
                            Color(0xFF1565C0)
                    )
                ) {

                    Text(
                        text = "Edit"
                    )
                }

                Button(

                    onClick = {

                    },

                    colors = ButtonDefaults.buttonColors(

                        containerColor =
                            Color(0xFFC62828)
                    )
                ) {

                    Text(
                        text = "Disable"
                    )
                }
            }
        }
    }
}
