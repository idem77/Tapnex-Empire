package com.tapnexempire.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tapnexempire.data.model.*

class CharacterViewModel : ViewModel() {

    var characterState by mutableStateOf(

        CharacterModel(

            id = "empire_king",

            name = "Empire Warrior"
        )
    )

    private set

    // 👑 CHANGE POSE
    fun changePose(
        pose: CharacterPose
    ) {

        characterState =
            characterState.copy(

                currentPose = pose
            )
    }   

          //🫅🏻 Change AURA
          fun changeAura(
    rune: String
) {

    characterState =
        characterState.copy(

            selectedRune = rune
        )
          }

    // 👑 EQUIP BUNDLE
    fun equipBundle(
bundle: BundleModel
) {

characterState =
    characterState.copy(

        selectedBundle = bundle,

        selectedRune =
            bundle.defaultRune,

        selectedMagic =
            bundle.defaultMagic
    )

    }
    
     // 🔮MAGIC EFFECT
       fun changeMagic(
    magic: String
) {

    characterState =
        characterState.copy(

            selectedMagic = magic
        )
       }

    // 👑 CHANGE RANK
    fun updateRank(
        rank: String
    ) {

        characterState =
            characterState.copy(
                rank = rank
            )
    }
}
