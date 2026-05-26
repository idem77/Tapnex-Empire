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

val character =
    viewModel.characterState.value

Box(

    modifier = Modifier
        .fillMaxWidth()
        .height(420.dp),

    contentAlignment = Alignment.Center
) {

    // 👑 WINGS
    Image(

        painter = painterResource(

            id = CharacterImageMapper
                .getWingsImage(
                    character.currentWings
                )
        ),

        contentDescription = null,

        modifier = Modifier
            .fillMaxWidth(),

        contentScale = ContentScale.Fit
    )

    // 👑 AURA
    Image(

        painter = painterResource(

            id = CharacterImageMapper
                .getAuraImage(
                    character.currentAura
                )
        ),

        contentDescription = null,

        modifier = Modifier
            .fillMaxWidth(),

        contentScale = ContentScale.Fit
    )

    // 👑 CHARACTER
    Image(

        painter = painterResource(

            id = CharacterImageMapper
                .getPoseImage(
                    character.currentPose
                )
        ),

        contentDescription = null,

        modifier = Modifier
            .fillMaxWidth(),

        contentScale = ContentScale.Fit
    )

    // 👑 WEAPON
    Image(

        painter = painterResource(

            id = CharacterImageMapper
                .getWeaponImage(
                    character.equippedItems.weapon
                )
        ),

        contentDescription = null,

        modifier = Modifier
            .fillMaxWidth(),

        contentScale = ContentScale.Fit
    )
}

}
