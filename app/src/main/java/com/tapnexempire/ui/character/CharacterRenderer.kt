package com.tapnexempire.ui.character

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tapnexempire.viewmodel.CharacterViewModel

@Composable
fun CharacterRenderer(

    viewModel: CharacterViewModel

) {

    val characterState =
        viewModel.characterState

    Box(

        modifier = Modifier
            .fillMaxWidth()
            .height(420.dp),

        contentAlignment =
            Alignment.Center

    ) {

        // 👑 Rune
        Image(

            painter = painterResource(

                id = CharacterImageMapper
                    .getAuraImage(
                        characterState.selectedRune
                    )
            ),

            contentDescription = null,

            modifier = Modifier.fillMaxWidth(),

            contentScale = ContentScale.Fit
        )

        // 🔮 Magic
        Image(

            painter = painterResource(

                id = CharacterImageMapper
                    .getMagicImage(
                        characterState.selectedMagic
                    )
            ),

            contentDescription = null,

            modifier = Modifier.fillMaxWidth(),

            contentScale = ContentScale.Fit
        )

        // 🫅 Bundle
        Image(

            painter = painterResource(

                id = CharacterImageMapper
                    .getBundleImage(
                        characterState.selectedBundle
                    )
            ),

            contentDescription = null,

            modifier = Modifier.fillMaxWidth(),

            contentScale = ContentScale.Fit
        )

        // 👑 Pose
        Image(

            painter = painterResource(

                id = CharacterImageMapper
                    .getPoseImage(
                        characterState.currentPose
                    )
            ),

            contentDescription = null,

            modifier = Modifier.fillMaxWidth(),

            contentScale = ContentScale.Fit
        )
    }
}
