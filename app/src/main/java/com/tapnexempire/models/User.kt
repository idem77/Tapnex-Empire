package com.tapnexempire.models

data class User(
    val id: String,                // Unique user ID
    val name: String,              // User's display name
    val email: String? = null,     // Optional email
    val coins: Double = 0.0,       // Total coins user has
    val depositBalance: Double = 0.0, // Coins deposited (non-withdrawable)
    val withdrawableBalance: Double = 0.0, // Coins that can be used for games/tasks
    val referralRewards: Double = 0.0, // Coins earned from referrals
    val joinedTournaments: List<String> = emptyList() // List of tournament IDs
)
