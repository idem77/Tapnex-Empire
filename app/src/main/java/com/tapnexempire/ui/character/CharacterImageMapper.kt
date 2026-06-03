package com.tapnexempire.ui.character

import com.tapnexempire.R
import com.tapnexempire.data.model.BundleModel
import com.tapnexempire.data.model.CharacterPose

object CharacterImageMapper {

// 👑 POSE IMAGES
fun getPoseImage(
    pose: CharacterPose
): Int {

    return when (pose) {

    CharacterPose.IDLE ->
        R.drawable.pose_idle

    CharacterPose.BATTLE ->
        R.drawable.pose_battle

    CharacterPose.AURA ->
        R.drawable.pose_aura

    CharacterPose.DIVINE ->
        R.drawable.pose_divine

    CharacterPose.THRONE ->
        R.drawable.pose_throne

    CharacterPose.VICTORY ->
        R.drawable.pose_victory

    CharacterPose.MEDITATION ->
        R.drawable.pose_meditation
 }
}

// 👑 BUNDLE IMAGES
fun getBundleImage(
    bundle: BundleModel
): Int {

    return when (bundle.id) {

        "lightbringer" ->
            R.drawable.bundle_light_bringer

        "celestial_divine" ->
            R.drawable.bundle_celestial_divine

        else ->
            R.drawable.bundle_void_monarch
    }
}

 // 🔮 MAGIC EFFECT IMAGES
   // 👑 MAGIC EFFECTS

fun getMagicImage(
    magicId: String
): Int {

    return when (magicId) {

        "void_flame" ->
            R.drawable.void_flame

        "dark_flame" ->
            R.drawable.dark_flame

        "divine_rune" ->
            R.drawable.divine_rune

        "lightning_orb" ->
            R.drawable.lightning_orb

        "spirit_energy" ->
            R.drawable.spirit_energy

        else ->
            0
    }
}

// 👑 AURA IMAGES
fun getAuraImage(
    auraId: String
): Int {

    return when(auraId){


        "void_aura" ->
            R.drawable.void_rune


        "lightning_aura" ->
            R.drawable.blood_rune

        else ->
            R.drawable.divine_rune
    }
  }
}

