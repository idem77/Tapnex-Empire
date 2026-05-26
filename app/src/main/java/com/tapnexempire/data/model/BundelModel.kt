package com.tapnexempire.data.model

data class BundleModel(

    val id: String = "",

    val title: String = "",

    val rarity: String = "",

    val price: Int = 0,

    val previewImage: String = "",

    val auraType: String = "",

    val weaponType: String = "",

    val wingsType: String = "",

    val isLimited: Boolean = false,

    val isActive: Boolean = true
)
