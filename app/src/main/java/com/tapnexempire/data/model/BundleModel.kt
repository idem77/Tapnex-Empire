package com.tapnexempire.data.model

data class BundleModel(

val id: String = "",

val title: String = "",

val rarity: String = "",

val bundleImage: String = "",

val defaultAura: String = "",

val defaultMagic: String = "",

val isLimited: Boolean = false

)    

val bundles = listOf(

BundleModel(

    id = "shadow_reaper",

    title = "Shadow Reaper",

    rarity = "Legendary",

    bundleImage = "bundle_shadow_reaper",

    defaultAura = "void_aura",

    defaultMagic = "shadow_orb"
),

BundleModel(

    id = "lightbringer",

    title = "Light Bringer",

    rarity = "Epic",

    bundleImage = "bundle_lightbringer",

    defaultAura = "light_aura",

    defaultMagic = "lightning_orb"
),

BundleModel(

    id = "celestial_divine",

    title = "Celestial Divine",

    rarity = "Mythic",

    bundleImage = "bundle_celestial_divine",

    defaultAura = "divine_aura",

    defaultMagic = "spirit_energy"
)

)
