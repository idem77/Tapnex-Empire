package com.tapnexempire.ui.equipment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.R
import com.tapnexempire.data.model.CharacterPose
import com.tapnexempire.viewmodel.CharacterViewModel

@Composable
fun EquipmentScreen(

    viewModel: CharacterViewModel =
        hiltViewModel()
) {

    val character =
        viewModel.characterState.value

    val poses = listOf(

        CharacterPose.STANDING,

        CharacterPose.BATTLE,

        CharacterPose.AURA,

        CharacterPose.MAGIC
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // 👑 BACKGROUND
        Image(

            painter =
                painterResource(
                    id = R.drawable.empire_bg
                ),

            contentDescription = null,

            modifier = Modifier.fillMaxSize(),

            contentScale = ContentScale.FillHeight
        )

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),

            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            Text(

                text = "⚔ Equipment",

                style =
                    MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 👑 CHARACTER
            Image(

                painter =
                    painterResource(
                        id = R.drawable.character
                    ),

                contentDescription = null,

                modifier =
                    Modifier
                        .height(320.dp)
                        .fillMaxWidth(),

                contentScale =
                    ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(

                text =
                    "Current Pose: ${character.currentPose}",

                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 👑 POSE SWITCHER
            LazyRow(

                horizontalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                items(poses) { pose ->

                    Button(

                        onClick = {

                            viewModel.changePose(
                                pose
                            )
                        }

                    ) {

                        Text(
                            text = pose.name
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(

                text =
                    "Future Equipment & Bundles 😏🔥",

                fontSize = 16.sp
            )
        }
    }
}
