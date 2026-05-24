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

    // 👑 EQUIP BUNDLE
    fun equipBundle(
        bundle: BundleModel
    ) {

        characterState =
            characterState.copy(

                selectedBundle = bundle
            )
    }

    // 👑 UPDATE EQUIPMENT
    fun updateEquipment(
        equipment: EquipmentModel
    ) {

        characterState =
            characterState.copy(

                equippedItems = equipment
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
