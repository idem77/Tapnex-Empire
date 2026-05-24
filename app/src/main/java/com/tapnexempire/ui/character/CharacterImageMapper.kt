package com.tapnexempire.ui.character

import com.tapnexempire.R
import com.tapnexempire.data.model.CharacterPose
import com.tapnexempire.data.model.EquipmentType

object CharacterImageMapper {

    fun getPoseImage(
        pose: CharacterPose
    ): Int {

        return when (pose) {

            CharacterPose.STANDING ->
                R.drawable.pose_standing

            CharacterPose.SWORD_READY ->
                R.drawable.pose_sword_ready

            CharacterPose.BATTLE ->
                R.drawable.pose_battle

            CharacterPose.MAGIC ->
                R.drawable.pose_magic

            CharacterPose.THRONE ->
                R.drawable.pose_throne

            CharacterPose.VICTORY ->
                R.drawable.pose_victory

            CharacterPose.GUARD ->
                R.drawable.pose_guard

            CharacterPose.DARK_AURA ->
                R.drawable.pose_dark_aura

            CharacterPose.WINGS ->
                R.drawable.pose_wings

            CharacterPose.POWERUP ->
                R.drawable.pose_powerup

            CharacterPose.LOOK_LEFT ->
                R.drawable.pose_look_left

            CharacterPose.LOOK_RIGHT ->
                R.drawable.pose_look_right
        }
    }

    fun getEquipmentImage(
        equipment: EquipmentType
    ): Int {

        return when (equipment) {

            EquipmentType.ROYAL_SWORD ->
                R.drawable.weapon_royal_sword

            EquipmentType.SHADOW_BLADE ->
                R.drawable.weapon_shadow_blade

            EquipmentType.FIRE_SWORD ->
                R.drawable.weapon_fire_sword

            EquipmentType.ICE_SWORD ->
                R.drawable.weapon_ice_sword

            EquipmentType.LIGHTNING_SWORD ->
                R.drawable.weapon_lightning_sword

            EquipmentType.DRAGON_SWORD ->
                R.drawable.weapon_dragon_sword

            EquipmentType.DIVINE_SPEAR ->
                R.drawable.weapon_divine_spear
        }
    }
}
