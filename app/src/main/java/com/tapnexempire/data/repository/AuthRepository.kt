package com.tapnexempire.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {

    suspend fun handleGoogleLogin(idToken: String): Boolean {

        return try {

            val credential = GoogleAuthProvider.getCredential(idToken, null)

            val result = auth.signInWithCredential(credential).await()
            val user = result.user ?: return false

            val uid = user.uid

            // 👤 USER CREATE
            val userRef = firestore.collection("users").document(uid)

            if (!userRef.get().await().exists()) {
                userRef.set(
                    mapOf(
                        "name" to (user.displayName ?: "User"),
                        "email" to (user.email ?: ""),
                        "createdAt" to System.currentTimeMillis()
                    )
                ).await()
            }

            // 💰 WALLET CREATE
            val walletRef = firestore.collection("wallets").document(uid)

            if (!walletRef.get().await().exists()) {
                walletRef.set(
                    mapOf(
                        "userId" to uid,
                        "depositCoins" to 0,
                        "bonusCoins" to 500,
                        "withdrawableCoins" to 0,
                        "dailyWithdrawnCoins" to 0,
                        "lastWithdrawDate" to 0
                    )
                ).await()
            }

            println("✅ LOGIN + FIRESTORE SUCCESS 👉 $uid")

            true

        } catch (e: Exception) {

            println("❌ LOGIN ERROR 👉 ${e.message}")
            false
        }
    }
}
