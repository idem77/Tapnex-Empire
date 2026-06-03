package com.tapnexempire.data.model

data class CharacterModel(

    val id: String = "",

    val name: String = "",

    val level: Int = 1,

    val rank: String = "Beginner",

    val currentPose:
        CharacterPose = CharacterPose.IDLE,

    val selectedBundle:
        BundleModel = BundleModel(),

    val selectedAura: String =
        "light_aura",
    
    val selectedMagic: String =
    "none"
)
