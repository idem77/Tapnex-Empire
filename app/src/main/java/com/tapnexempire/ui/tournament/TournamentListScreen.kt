package com.tapnexempire.ui.tournament

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tapnexempire.R
import com.tapnexempire.data.model.TournamentModel
import com.tapnexempire.navigation.Routes
import com.tapnexempire.ui.components.EmpireTournamentCard
import com.tapnexempire.ui.theme.EmpireGold
import com.tapnexempire.ui.theme.EmpireWhite
import com.tapnexempire.viewmodel.TournamentViewModel

@Composable
fun TournamentListScreen(

    navController: NavController,

    viewModel: TournamentViewModel,

    userId: String
) {

    var tournaments by remember {

        mutableStateOf<List<TournamentModel>>(emptyList())
    }

    LaunchedEffect(Unit) {

        viewModel.listenTournaments { list ->

            tournaments = list
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // 👑 BACKGROUND
        Image(
            painter = painterResource(id = R.drawable.tournament_bg),
            contentDescription = null,
            modifier = Modifier.Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp)
                .padding(top = 50.dp)
        ) {

            // 👑 TITLE
            Text(

                text = "🏆 Empire Tournaments",

                style = MaterialTheme.typography.headlineLarge,

                color = EmpireWhite
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(

                text = "Compete • Win • Dominate",

                color = EmpireGold,

                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(

                verticalArrangement =
                    Arrangement.spacedBy(16.dp),

                contentPadding =
                    PaddingValues(bottom = 100.dp)
            ) {

                items(tournaments) { tournament ->

                    EmpireTournamentCard(

                        title = tournament.title,

                        entryFee =
                            tournament.entryFee,

                        prizePool =
                            tournament.prizePool,

                        joinedPlayers =
                            tournament.joinedPlayers,

                        maxPlayers =
                            tournament.maxPlayers,

                        endTime = tournament.endTime,

                        status =
                            tournament.status,

                        onJoin = {

                            navController.navigate(

                                "${Routes.TOURNAMENT_DETAIL}/${tournament.id}"
                            )
                        }
                    )
                }
            }
        }
    }
}
