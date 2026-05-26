package com.tapnexempire.ui.character

import com.tapnexempire.R
import com.tapnexempire.data.model.CharacterPose

object CharacterImageMapper {

// 👑 CHARACTER POSE IMAGE
fun getPoseImage(
    pose: CharacterPose
): Int {

    return when (pose) {

        CharacterPose.STANDING ->
            R.drawable.pose_standing

        CharacterPose.BATTLE ->
            R.drawable.pose_battle

        CharacterPose.MAGIC ->
            R.drawable.pose_magic

        CharacterPose.THRONE ->
            R.drawable.pose_throne

        else ->
            R.drawable.pose_standing
    }
}

// 👑 AURA IMAGE
fun getAuraImage(
    aura: String
): Int {

    return when (aura) {

        "FIRE" ->
            R.drawable.aura_fire

        "ICE" ->
            R.drawable.aura_ice

        "DARK" ->
            R.drawable.aura_dark

        else ->
            R.drawable.aura_none
    }
}

// 👑 WEAPON IMAGE
fun getWeaponImage(
    weapon: String
): Int {

    return when (weapon) {

        "ROYAL_SWORD" ->
            R.drawable.weapon_royal_sword

        "SHADOW_BLADE" ->
            R.drawable.weapon_shadow_blade

        else ->
            R.drawable.weapon_none
    }
}

// 👑 WINGS IMAGE
fun getWingsImage(
    wings: String
): Int {

    return when (wings) {

        "ANGEL" ->
            R.drawable.wings_angel

        "DARK" ->
            R.drawable.wings_dark

        else ->
            R.drawable.wings_none
    }
}

}
