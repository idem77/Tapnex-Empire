package com.tapnexempire.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.tapnexempire.R
import com.tapnexempire.ui.theme.*

@Composable
fun HomeScreen(
    coins: Int,
    onWalletClick: () -> Unit,
    onTournamentClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightCream)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Coins Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .shadow(4.dp, RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
        ) {
            Box(
                modifier = Modifier
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(Gold.copy(alpha = 0.8f), Gold)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_coin),
                        contentDescription = "Coins",
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Total Coins",
                            color = SoftGray,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "$coins",
                            color = CharcoalBlack,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Tiles Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Tournament Tile
            TileCard(
                title = "Tournament",
                iconRes = R.drawable.ic_crown,
                onClick = onTournamentClick
            )

            // Wallet Tile
            TileCard(
                title = "Wallet",
                iconRes = R.drawable.ic_wallet,
                onClick = onWalletClick
            )

            // Profile Tile
            TileCard(
                title = "Profile",
                iconRes = R.drawable.ic_logo,
                onClick = onProfileClick
            )
        }
    }
}

@Composable
fun TileCard(
    title: String,
    iconRes: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .size(100.dp)
            .clickable { onClick() }
            .shadow(4.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
    ) {
        Box(
            modifier = Modifier
                .background(PinkPeachLight),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = title,
                    modifier = Modifier.size(36.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = title,
                    color = CharcoalBlack,
                    fontSize = 14.sp
                )
            }
        }
    }
}
