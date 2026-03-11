package com.tapnexempire.data.model

data class UserModel(
    val uid: String,
    val phone: String,
    val isNewUser: Boolean = false
)
