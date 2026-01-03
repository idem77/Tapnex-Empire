package com.tapnexempire.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class WalletRepository(
    private val firestore: FirebaseFirestore
) {

    suspend fun getWallet(userId: String): Wallet {
        val doc = firestore.collection("wallets")
            .document(userId)
            .get()
            .await()

        return doc.toObject(Wallet::class.java) ?: Wallet()
    }

    suspend fun createWalletIfNotExists(userId: String) {
        val ref = firestore.collection("wallets").document(userId)
        if (!ref.get().await().exists()) {
            ref.set(Wallet()).await()
        }
    }

    suspend fun addDepositCoins(userId: String, coins: Int) {
        firestore.runTransaction { tx ->
            val ref = firestore.collection("wallets").document(userId)
            val wallet = tx.get(ref).toObject(Wallet::class.java) ?: Wallet()
            tx.set(
                ref,
                wallet.copy(depositCoins = wallet.depositCoins + coins)
            )
        }.await()
    }

    suspend fun addBonusCoins(userId: String, coins: Int) {
        firestore.runTransaction { tx ->
            val ref = firestore.collection("wallets").document(userId)
            val wallet = tx.get(ref).toObject(Wallet::class.java) ?: Wallet()
            tx.set(
                ref,
                wallet.copy(bonusCoins = wallet.bonusCoins + coins)
            )
        }.await()
    }

    suspend fun addWithdrawableCoins(userId: String, coins: Int) {
        firestore.runTransaction { tx ->
            val ref = firestore.collection("wallets").document(userId)
            val wallet = tx.get(ref).toObject(Wallet::class.java) ?: Wallet()
            tx.set(
                ref,
                wallet.copy(withdrawableCoins = wallet.withdrawableCoins + coins)
            )
        }.await()
    }

    suspend fun deductWithdrawableCoins(userId: String, coins: Int) {
        firestore.runTransaction { tx ->
            val ref = firestore.collection("wallets").document(userId)
            val wallet = tx.get(ref).toObject(Wallet::class.java) ?: Wallet()

            if (wallet.withdrawableCoins < coins) {
                throw Exception("Insufficient balance")
            }

            tx.set(
                ref,
                wallet.copy(withdrawableCoins = wallet.withdrawableCoins - coins)
            )
        }.await()
    }
}
