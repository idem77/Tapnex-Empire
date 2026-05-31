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
import com.tapnexempire.R
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
            .height(520.dp),

        contentAlignment =
            Alignment.Center
    ) {

        // 👑 AURA
        Image(

            painter = painterResource(

                id = CharacterImageMapper
                    .getAuraImage(
                        characterState.selectedAura
                    )
            ),

            contentDescription = null,

            modifier = Modifier
                .fillMaxWidth(),

            contentScale = ContentScale.Fit
        )

        // 👑 CHARACTER TEST
        Image(

            painter = painterResource(
                id = R.drawable.pose_idle
            ),

            contentDescription = null,

            modifier = Modifier
                .fillMaxWidth(),

            contentScale = ContentScale.Fit
        )
    }
}
