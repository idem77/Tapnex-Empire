package com.tapnexempire.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.data.model.WalletModel
import javax.inject.Inject

class WalletRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    private val walletRef = firestore.collection("wallets")

    // 👀 Real-time wallet listener
    fun listenToWallet(userId: String, onChange: (WalletModel?) -> Unit) {

        println("🔥 TRYING DOC ID 👉 [$userId]")

        walletRef.document(userId)
            .addSnapshotListener { snapshot, error ->

                if (error != null) {
                    println("❌ FIRESTORE ERROR 👉 ${error.message}")
                    onChange(null)
                    return@addSnapshotListener
                }

                if (snapshot != null && snapshot.exists()) {
                    println("✅ DOCUMENT FOUND")

                    val data = snapshot.data
                    println("📦 RAW DATA 👉 $data")

                    // 🔥 SAFE MANUAL PARSING (NO toObject())
                    val wallet = WalletModel(
                        userId = data?.get("userId") as? String ?: "",
                        depositCoins = (data?.get("depositCoins") as? Long) ?: 0,
                        bonusCoins = (data?.get("bonusCoins") as? Long) ?: 0,
                        withdrawableCoins = (data?.get("withdrawableCoins") as? Long) ?: 0,
                        dailyWithdrawnCoins = (data?.get("dailyWithdrawnCoins") as? Long) ?: 0,
                        lastWithdrawDate = (data?.get("lastWithdrawDate") as? Long) ?: 0
                    )

                    println("💰 PARSED WALLET 👉 $wallet")

                    onChange(wallet)

                } else {
                    println("❌ DOCUMENT NOT FOUND")
                    onChange(null)
                }
            }
    }
}
