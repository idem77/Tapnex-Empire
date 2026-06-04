package com.tapnexempire.ui.equipment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapnexempire.R
import com.tapnexempire.data.model.CharacterPose
import com.tapnexempire.data.model.bundles
import com.tapnexempire.ui.character.CharacterRenderer
import com.tapnexempire.viewmodel.CharacterViewModel

@Composable
fun EquipmentScreen(

    viewModel: CharacterViewModel =
        hiltViewModel()

) {

    val character =
        viewModel.characterState

    val poses = listOf(

        CharacterPose.IDLE,
        CharacterPose.BATTLE,
        CharacterPose.AURA,
        CharacterPose.DIVINE,
        CharacterPose.THRONE,
        CharacterPose.VICTORY,
        CharacterPose.MEDITATION
    )

    val runes = listOf(

        "light_rune",
        "void_rune",
        "divine_rune"
    )

    val magicEffects = listOf(

        "dark_flame",
        "shadow_orb",
        "spirit_energy",
        "lightning_orb"
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(

            painter = painterResource(
                id = R.drawable.empire_bg
            ),

            contentDescription = null,

            modifier = Modifier.fillMaxSize(),

            contentScale =
                ContentScale.FillHeight
        )

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),

            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            Spacer(
                modifier = Modifier.height(40.dp)
            )

             Column(
    modifier = Modifier
        .fillMaxSize()
        .verticalScroll(
            rememberScrollState()
        )
        .padding(20.dp),
        )

            Text(

                text = "⚔ Equipment",

                style =
                    MaterialTheme
                        .typography
                        .headlineLarge
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            CharacterRenderer(
                viewModel = viewModel
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Text(

                text =
                    "Current Pose: ${character.currentPose}",

                fontSize = 18.sp
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // 👑 POSES

            Text("👑 Poses")

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
                            pose.name
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // 👑 RUNES

            Text("👑 Runes")

            LazyRow(

                horizontalArrangement =
                    Arrangement.spacedBy(12.dp)

            ) {

                items(runes) { rune ->

                    Button(

                        onClick = {

                            viewModel.changeAura(
                                rune
                            )
                        }
                    ) {

                        Text(rune)
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // 🔮 MAGIC

            Text("🔮 Magic")

            LazyRow(

                horizontalArrangement =
                    Arrangement.spacedBy(12.dp)

            ) {

                items(magicEffects) { effect ->

                    Button(

                        onClick = {

                            viewModel.changeMagic(
                                effect
                            )
                        }
                    ) {

                        Text(effect)
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // ⚔ BUNDLES

            Text("⚔ Bundles")

            LazyRow(

                horizontalArrangement =
                    Arrangement.spacedBy(12.dp)

            ) {

                items(bundles) { bundle ->

                    Button(

                        onClick = {

                            viewModel.equipBundle(
                                bundle
                            )
                        }
                    ) {

                        Text(
                            bundle.title
                        )
                    }
                }
            }
        }
    }
}
