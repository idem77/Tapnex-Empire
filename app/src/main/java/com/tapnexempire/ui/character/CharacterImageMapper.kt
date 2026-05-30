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
}

