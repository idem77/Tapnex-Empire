package com.tapnexempire.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FieldValue
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

                val wallet = snapshot.toObject(WalletModel::class.java)

                println("💰 PARSED WALLET 👉 $wallet")

                onChange(wallet)

            } else {
                println("❌ DOCUMENT NOT FOUND")
                onChange(null)
            }
        }
    }
