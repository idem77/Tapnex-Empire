package com.tapnexempire.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.data.model.BundleModel
import kotlinx.coroutines.tasks.await

class BundleRepository {

    private val firestore =
        FirebaseFirestore.getInstance()

    suspend fun getBundles():
            List<BundleModel> {

        return try {

            firestore
                .collection("bundles")
                .get()
                .await()
                .documents
                .mapNotNull {

                    it.toObject(
                        BundleModel::class.java
                    )
                }

        } catch (e: Exception) {

            emptyList()
        }
    }
}
