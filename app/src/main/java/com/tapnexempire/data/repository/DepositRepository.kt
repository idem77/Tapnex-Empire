package com.tapnexempire.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class DepositRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    private val depositRef =
        firestore.collection("deposit_requests")

    fun createDepositRequest(
        userId: String,
        amountRupees: Long,
        amountCoins: Long,
        upiRef: String,
        screenshotUrl: String,
        onResult: (Boolean, String) -> Unit
    ) {

        val requestId = depositRef.document().id

        val data = hashMapOf(

            "id" to requestId,

            "userId" to userId,

            "amountRupees" to amountRupees,

            "amountCoins" to amountCoins,

            "upiRef" to upiRef,

            "screenshotUrl" to screenshotUrl,

            "status" to "pending",

            "createdAt" to System.currentTimeMillis(),

            "approvedAt" to "",

            "approvedBy" to ""
        )

        depositRef.document(requestId)
            .set(data)
            .addOnSuccessListener {

                onResult(
                    true,
                    "Deposit request submitted"
                )
            }
            .addOnFailureListener {

                onResult(
                    false,
                    it.message ?: "Failed"
                )
            }
    }
}
