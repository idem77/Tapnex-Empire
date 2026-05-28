package com.tapnexempire.ui.character

import com.tapnexempire.R
import com.tapnexempire.data.model.CharacterPose

object CharacterImageMapper {

// 👑 POSE IMAGES
fun getPoseImage(
    pose: CharacterPose
): Int {

    return when (pose) {

        CharacterPose.DIVINE ->
            R.drawable.pose_divine

        CharacterPose.THRONE ->
            R.drawable.pose_throne
    }
}

// 👑 BUNDLE IMAGES
fun getBundleImage(
    bundleId: String
): Int {

    return when (bundleId) {

        "lightbringer" ->
            R.drawable.bundle_lightbringer

        "celestial_divine" ->
            R.drawable.bundle_celestial_divine

        else ->
            R.drawable.bundle_shadow_reaper
    }
}

// 👑 AURA IMAGES
fun getAuraImage(
    auraId: String
): Int {

    return when (auraId) {

        "light_aura" ->
            R.drawable.aura_light

        else ->
            R.drawable.aura_light
    }
}

// 👑 WEAPON IMAGES
fun getWeaponImage(
    weaponId: String
): Int {

    return when (weaponId) {

        "ember_blade" ->
            R.drawable.weapon_ember_blade

        else ->
            R.drawable.weapon_light_sword
    }
}

// 👑 WINGS IMAGES
fun getWingsImage(
    wingsId: String
): Int {

    return when (wingsId) {

        "angel_wings" ->
            R.drawable.wings_angel

        "celestial_wings" ->
            R.drawable.celestial_wings

        else ->
            R.drawable.wings_shadow
    }
}

}
