package com.tapnexempire.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.shadow
import androidx.compose.runtime.*
import com.google.firebase.auth.FirebaseAuth
import com.tapnexempire.security.AdminSecurity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tapnexempire.R
import com.tapnexempire.navigation.Routes
import com.tapnexempire.ui.theme.EmpireGold
import com.tapnexempire.ui.theme.EmpireWhite
import com.tapnexempire.viewmodel.CharacterViewModel

@Composable
fun HomeScreen(

navController: NavController,

characterViewModel: CharacterViewModel =
    hiltViewModel()

) {

val characterState =
    characterViewModel.characterState
  var isAdmin by remember {
    mutableStateOf(false)
}

LaunchedEffect(Unit) {

    val uid = FirebaseAuth
        .getInstance()
        .currentUser
        ?.uid

    if (uid != null) {

        AdminSecurity.isAdmin(uid) { admin ->

            isAdmin = admin
        }
    }
}

Box(
    modifier = Modifier.fillMaxSize()
) {

    // 👑 BACKGROUND
    Image(

        painter = painterResource(
            id = R.drawable.empire_bg
        ),

        contentDescription = null,

        modifier = Modifier.matchParentSize(),

        contentScale = ContentScale.Crop,

        alignment = Alignment.Center
    )

    // 👑 CONTENT
    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(bottom = 90.dp)
            .verticalScroll(
                rememberScrollState()
            ),

        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {

        Spacer(
            modifier = Modifier.height(45.dp)
        )

        // 👑 TITLE
        Text(

            text = "👑 Tapnex Empire",

            style =
                MaterialTheme.typography.headlineLarge,

            color = EmpireWhite
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )
    )

        Text(

            text =
                "Rise Through The Empire 😏🔥",

            color = EmpireGold,

            fontSize = 16.sp
        )

        {

            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),

                verticalArrangement =
                    Arrangement.Center
            ) {

                Text(

                    text =
                        characterState.selectedBundle.title,

                    color = Color.White
                )
            }
        }

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        // ⚔ EQUIPMENT CARD
        Card(

            modifier = Modifier
                .fillMaxWidth()
                .height(95.dp)
                .shadow(12.dp)
                .clickable {

                    navController.navigate(
                        Routes.EQUIPMENT
                    )
                },

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

                    text = "⚔ Equipment",

                    style =
                        MaterialTheme.typography.titleLarge,

                    color = Color.White
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(

                    text =
                        "Upgrade your warrior & unlock legendary power 😏🔥",

                    color = Color.LightGray
                )
            }
        }

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        // 🏆 TOURNAMENT CARD
        Card(

            modifier = Modifier
                .fillMaxWidth()
                .height(95.dp)
                .shadow(12.dp)
                .clickable {

                    navController.navigate(
                        Routes.TOURNAMENTS
                    )
                },

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

                    text = "🏆 Battle Arena",

                    style =
                        MaterialTheme.typography.titleLarge,

                    color = Color.White
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(

                    text =
                        "Enter tournaments & dominate the empire 😏🔥",

                    color = Color.LightGray
                )
            }
        }

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        // 🌊 LEADERBOARD CARD

Card(

    modifier = Modifier
        .fillMaxWidth()
        .height(95.dp)
        .shadow(12.dp)
        .clickable {

            navController.navigate(
                Routes.LEADERBOARD
            )
        },

    colors = CardDefaults.cardColors(

        containerColor =
            Color(0xCC102A43)
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

            text = "🌊 Ocean Leaderboard",

            style =
                MaterialTheme.typography.titleLarge,

            color = Color.White
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Text(

            text =
                "Weekly Rankings & Empire Rewards 👑",

            color = Color.LightGray
        )
    }
}

      // 🫅🏻 Admin 
        if (isAdmin) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(95.dp)
            .clickable {

                navController.navigate(
                    "admin_dashboard"
                )
            },

        colors = CardDefaults.cardColors(
            containerColor = Color(0xCC2A1822)
        )
    ) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

             Text(
    text = "👑 Open Admin Panel",
    color = Color.White,
    style = MaterialTheme.typography.titleLarge
)
        }
    }
}

Spacer(
    modifier = Modifier.height(30.dp)
)

    }

}

}
