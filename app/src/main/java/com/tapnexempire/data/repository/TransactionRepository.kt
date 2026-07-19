package com.tapnexempire.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.data.model.TransactionModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    fun listenToTransactions(
        userId: String,
        onChange: (List<TransactionModel>) -> Unit
    ) {

        firestore.collection("transactions")
            .document(userId)
            .collection("history")
            .orderBy("createdAt")
            .addSnapshotListener { snapshot, error ->

                if (error != null || snapshot == null) {

                    println("❌ Transaction Listener Error")

                    onChange(emptyList())
                    return@addSnapshotListener
                }

                val transactions = snapshot.documents.mapNotNull { doc ->

                    try {

                        TransactionModel(

                            id =
                                doc.getString("id") ?: "",

                            userId =
                                doc.getString("userId") ?: "",

                            type =
                                doc.getString("type") ?: "",

                            amount =
                                (doc.getLong("amount") ?: 0).toInt(),

                            coins =
                                (doc.getLong("coins") ?: 0).toInt(),

                            status =
                                doc.getString("status") ?: "",

                            description =
                                doc.getString("description") ?: "",

                            createdAt =
                                doc.getTimestamp("createdAt")
                                    ?.toDate()
                                    ?.time ?: 0
                        )

                    } catch (e: Exception) {

                        e.printStackTrace()
                        null
                    }
                }

                println("✅ Transactions Loaded: ${transactions.size}")

                onChange(transactions)
            }
    }

            suspend fun addTransaction(
    transaction: TransactionModel
): Result<Unit> {

    return try {

        firestore
            .collection("transactions")
            .document(transaction.userId)
            .collection("history")
            .document(transaction.id)
            .set(transaction)
            .await()

        Result.success(Unit)

    } catch (e: Exception) {

        Result.failure(e)

    }

     }
  }


