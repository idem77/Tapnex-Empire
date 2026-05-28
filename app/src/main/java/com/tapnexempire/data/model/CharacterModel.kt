package com.tapnexempire.data.model

data class CharacterModel(

    val id: String = "",

    val name: String = "",

    val level: Int = 1,

    val rank: String = "Beginner",

    val currentPose:
        CharacterPose = CharacterPose.IDLE,

    val equippedItems:
        EquipmentModel = EquipmentModel(),

    val selectedBundle:
        BundleModel = BundleModel()

    val selectedAura: String = "none",

     val selectedWings: String = "none",

     val selectedWeapon: String = "none"
    
)
