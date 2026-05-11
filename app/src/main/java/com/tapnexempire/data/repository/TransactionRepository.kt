package com.tapnexempire.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.data.model.TransactionModel
import com.tapnexempire.data.model.TransactionType
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
                                TransactionType.valueOf(
                                    doc.getString("type")
                                        ?: "DEPOSIT"
                                ),

                            amount =
                                doc.getLong("amount") ?: 0,

                            description =
                                doc.getString("description") ?: "",

                            createdAt =
                                doc.getLong("createdAt") ?: 0
                        )

                    } catch (e: Exception) {

                        null
                    }
                }

                onChange(transactions)
            }
    }
}
