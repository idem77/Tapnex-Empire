package com.tapnexempire.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import com.tapnexempire.data.model.WalletModel

class WalletRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    private val walletRef = firestore.collection("wallets")

    fun listenToWallet(userId: String, onChange: (WalletModel?) -> Unit) {
        walletRef.document(userId)
            .addSnapshotListener { snapshot, _ ->
                onChange(snapshot?.toObject(WalletModel::class.java))
            }
    }
}
