package com.tapnexempire.ui.tournament.detail

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tapnexempire.R
import com.tapnexempire.components.AppButton
import com.tapnexempire.navigation.Routes
import com.tapnexempire.ui.theme.EmpireGold
import com.tapnexempire.ui.theme.EmpireWhite
import com.tapnexempire.data.model.TournamentModel
import com.tapnexempire.viewmodel.TournamentViewModel

@Composable
fun TournamentDetailScreen(

    navController: NavController,

    tournamentId: String,

    userId: String,

    viewModel: TournamentViewModel =
        hiltViewModel()
) {

    var loading by remember {

        mutableStateOf(false)
    }

    var resultMessage by remember {

        mutableStateOf("")
    }

       var tournament by remember {
    mutableStateOf<TournamentModel?>(null)
}

LaunchedEffect(tournamentId) {

    viewModel.getTournamentById(
        tournamentId = tournamentId
    ) {
        tournament = it
    }
}

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // 👑 BACKGROUND
        Image(
            painter = painterResource(id = R.drawable.empire_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 50.dp)
                .verticalScroll(rememberScrollState()),

            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            // 👑 TITLE
            Text(

                text = "🏆 Tournament Arena",

                style =
                    MaterialTheme.typography.headlineLarge,

                color = EmpireWhite
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(

                text = "Enter • Compete • Win",

                color = EmpireGold,

                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(30.dp))
           
            // 👑 TOURNAMENT CARD
            Card(

                modifier = Modifier.fillMaxWidth(),

                colors = CardDefaults.cardColors(
                    containerColor =
                        MaterialTheme.colorScheme.surface
                ),

                elevation =
                    CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
            ) {

                Column(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(22.dp)
                ) {

                    Text(

                        text = "Tournament ID",

                        color = EmpireGold,

                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(

                        text = tournamentId,

                        color = EmpireWhite
                    )

                    Spacer(modifier = Modifier.height(20.dp))


Text(
    text =
        "🎟 Entry Fee: ${tournament?.entryFee ?: 0} Coins"
)

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(

                        text =
                            "⚔ Complete match to earn rewards",

                        color = EmpireWhite
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(

                        text =
                            "🏆 Top players receive biggest rewards",

                        color = EmpireWhite
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // 👑 JOIN BUTTON
            AppButton(

                text =

                    if (loading)
                        "Joining..."
                    else
                        "🚀 Join Tournament",

                enabled = !loading,

                onClick = {

                    loading = true

                    viewModel.joinTournament(

                        tournamentId = tournamentId,

                        userId = userId,

                        entryFee =
    tournament?.entryFee ?: 0,

                        onResult = { success, message ->

                            loading = false

                            resultMessage = message

                            if (success) {

                                val encodedUrl = Uri.encode(

                                    "https://plays.org/game/flappy-bird/"
                                )

                                navController.navigate(
                                     Routes.CASTLE_CLIMB
                       )
                     
                            }
                        }
                    )
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 👑 RESULT MESSAGE
            if (resultMessage.isNotEmpty()) {

                Card(

                    colors = CardDefaults.cardColors(
                        containerColor =
                            MaterialTheme.colorScheme.surface
                    )
                ) {

                    Text(

                        text = resultMessage,

                        modifier =
                            Modifier.padding(16.dp),

                        color = EmpireWhite
                    )
                }
            }

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}
