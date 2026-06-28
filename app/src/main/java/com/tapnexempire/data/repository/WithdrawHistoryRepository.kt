package com.tapnexempire.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.tapnexempire.data.model.WithdrawHistoryModel
import javax.inject.Inject

class WithdrawHistoryRepository @Inject constructor(

    private val firestore: FirebaseFirestore

) {

    fun listenWithdrawHistory(

        userId: String,

        onUpdate: (List<WithdrawHistoryModel>) -> Unit

    ): ListenerRegistration {

        return firestore

            .collection("withdraw_requests")

            .whereEqualTo("userId", userId)

            .addSnapshotListener { snapshot, _ ->

                val list =

                    snapshot?.documents?.mapNotNull {

                        it.toObject(
                            WithdrawHistoryModel::class.java
                        )

                    } ?: emptyList()

                onUpdate(
                    list.sortedByDescending {
                        it.createdAt
                    }
                )
            }
    }
}
