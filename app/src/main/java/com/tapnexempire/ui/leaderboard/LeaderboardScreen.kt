package com.tapnexempire.ui.leaderboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.R
import com.tapnexempire.data.model.LeaderboardPlayer
import com.tapnexempire.ui.components.LeaderboardPlayerCard
import com.tapnexempire.ui.theme.EmpireGold
import com.tapnexempire.ui.theme.EmpireWhite
import com.tapnexempire.viewmodel.LeaderboardViewModel

@Composable
fun LeaderboardScreen(

viewModel: LeaderboardViewModel =
    hiltViewModel()

) {

var players by remember {

    mutableStateOf<List<LeaderboardPlayer>>(
        emptyList()
    )
}

LaunchedEffect(Unit) {

    viewModel.listenLeaderboard {

        players = it
    }
}

Box(
    modifier = Modifier.fillMaxSize()
) {

    Image(
        painter = painterResource(
            id = R.drawable.leaderboard_ocean_bg
        ),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp)
            .padding(top = 40.dp)

    ) {

        Text(

            text = "👑 Empire Leaderboard",

            style =
                MaterialTheme.typography.headlineLarge,

            color = EmpireWhite
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text(

            text = "Top Players Of Tapnex Empire",

            color = EmpireGold,

            fontSize = 16.sp
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Box(

            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)

        ) {

            Image(
                painter = painterResource(
                    id = R.drawable.reward_hologram_frame
                ),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )

            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(28.dp)

            ) {

                Text(
                    text = "Weekly Prize Pool",
                    color = EmpireGold,
                    fontSize = 22.sp
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "50,000 Coins",
                    color = EmpireWhite,
                    fontSize = 28.sp
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                Text(
                    text = "#1 20,000 Coins",
                    color = EmpireWhite
                )

                Text(
                    text = "#2 10,000 Coins",
                    color = EmpireWhite
                )

                Text(
                    text = "#3 5,000 Coins",
                    color = EmpireWhite
                )
            }
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        LazyColumn(

            verticalArrangement =
                Arrangement.spacedBy(12.dp),

            contentPadding =
                PaddingValues(bottom = 100.dp)

        ) {

            itemsIndexed(players) {

                index,
                player ->

                LeaderboardPlayerCard(

                    rank = index + 1,

                    username =
                        player.username,

                    points =
                        player.empirePoints
                )
            }
        }
    }
}

}
