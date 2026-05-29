package com.tapnexempire.ui.character

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tapnexempire.ui.character.CharacterRenderer
import com.tapnexempire.data.model.BundleModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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

                    characterState
                        .selectedAura
                )
        ),

        contentDescription = null,

        modifier = Modifier
            .fillMaxWidth(),

        contentScale =
            ContentScale.Fit
    )

    // 👑 WINGS
    Image(

        painter = painterResource(

            id = CharacterImageMapper
                .getWingsImage(

                    characterState
                        .selectedWings
                )
        ),

        contentDescription = null,

        modifier = Modifier
            .fillMaxWidth(),

        contentScale =
            ContentScale.Fit
    )

    // 👑 BUNDLE / CHARACTER
    Image(

        painter = painterResource(

            id = CharacterImageMapper
                .getBundleImage(

                    characterState
                        .selectedBundle
                )
        ),

        contentDescription = null,

        modifier = Modifier
            .fillMaxWidth(),

        contentScale =
            ContentScale.Fit
    )

    // 👑 WEAPON
    Image(

        painter = painterResource(

            id = CharacterImageMapper
                .getWeaponImage(

                    characterState
                        .selectedWeapon
                )
        ),

        contentDescription = null,

        modifier = Modifier
            .fillMaxWidth(),

        contentScale =
            ContentScale.Fit
    )
}

}
