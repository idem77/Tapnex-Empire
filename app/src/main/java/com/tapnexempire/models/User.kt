package com.tapnexempire.models

data class UserModel(
    val uid: String,
    val phone: String,
    val isNewUser: Boolean = false
)
