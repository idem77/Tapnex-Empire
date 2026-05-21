package com.tapnexempire.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.tapnexempire.data.model.*

class CharacterViewModel : ViewModel() {

    var characterState =
        mutableStateOf(

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

        characterState.value =
            characterState.value.copy(

                currentPose = pose
            )
    }

    // 👑 EQUIP BUNDLE
    fun equipBundle(
        bundle: BundleModel
    ) {

        characterState.value =
            characterState.value.copy(

                selectedBundle = bundle
            )
    }

    // 👑 UPDATE EQUIPMENT
    fun updateEquipment(
        equipment: EquipmentModel
    ) {

        characterState.value =
            characterState.value.copy(

                equippedItems = equipment
            )
    }

    // 👑 CHANGE RANK
    fun updateRank(
        rank: String
    ) {

        characterState.value =
            characterState.value.copy(
                rank = rank
            )
    }
}
