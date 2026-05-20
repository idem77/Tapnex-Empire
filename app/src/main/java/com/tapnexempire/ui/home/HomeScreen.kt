package com.tapnexempire.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tapnexempire.R
import com.tapnexempire.components.GameTile
import com.tapnexempire.ui.components.EmpireBundleCard
import com.tapnexempire.ui.components.EmpireMissionCard
import com.tapnexempire.ui.components.EmpireTournamentCard
import com.tapnexempire.ui.components.EmpireWalletCard
import com.tapnexempire.ui.theme.EmpireGold
import com.tapnexempire.ui.theme.EmpireWhite

@Composable
fun HomeScreen() {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // 👑 BACKGROUND
        Image(
            painter = painterResource(id = R.drawable.home_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // 👑 CONTENT
        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp)
                .padding(bottom = 80.dp)
                .verticalScroll(rememberScrollState()),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(50.dp))

            // 👑 TITLE
            Text(

                text = "👑 Tapnex Empire",

                style = MaterialTheme.typography.headlineLarge,

                color = EmpireWhite
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(

                text = "Play • Win • Rise",

                color = EmpireGold,

                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(28.dp))

            // 💰 WALLET CARD
            EmpireWalletCard(

                depositCoins = 1200,

                withdrawableCoins = 450,

                bonusCoins = 100
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 🏆 FEATURED TOURNAMENT
            EmpireTournamentCard(

               title = "Ludo Battle",

                 entryFee = 240,

                   prizePool = 5000,

    joinedPlayers = 1,

    maxPlayers = 100,

    status = "LIVE",

    onJoin = {

    }
)

            Spacer(modifier = Modifier.height(24.dp))

            // 🎮 GAMES
            GameTile(

                gameName = "Ludo Supreme",

                reward = 500
            ) {

            }

            Spacer(modifier = Modifier.height(18.dp))

            GameTile(

                gameName = "Flappy Challenge",

                reward = 250
            ) {

            }

            Spacer(modifier = Modifier.height(24.dp))

            // 🎯 MISSIONS
            EmpireMissionCard(

                title = "Play 3 Matches",

                progress = 0.6f,

                rewardCoins = 300
            ) {

            }

            Spacer(modifier = Modifier.height(24.dp))

            // 💎 BUNDLE
            EmpireBundleCard(

                title = "Starter Bundle",

                coins = 5000,

                price = "₹99"
            ) {

            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}
