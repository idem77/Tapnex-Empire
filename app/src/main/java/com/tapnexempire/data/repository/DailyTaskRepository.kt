package com.tapnexempire.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.data.model.DailyTaskModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class DailyTaskRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {


    private val taskRef =
        firestore.collection("daily_tasks")


    suspend fun getDailyTasks():
            List<DailyTaskModel> {

        return try {

            val snapshot =
                taskRef
                    .whereEqualTo(
                        "isActive",
                        true
                    )
                    .get()
                    .await()


            snapshot.documents.map {

                it.toObject(
                    DailyTaskModel::class.java
                )?.copy(
                    id = it.id
                )
                ?: DailyTaskModel()

            }


        } catch (e: Exception) {

            emptyList()

        }

    }

}
