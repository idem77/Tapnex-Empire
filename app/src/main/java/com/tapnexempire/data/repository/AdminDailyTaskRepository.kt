package com.tapnexempire.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.tapnexempire.data.model.DailyTaskModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AdminDailyTaskRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    private val taskRef =
        firestore.collection("daily_tasks")

    suspend fun createTask(
        task: DailyTaskModel
    ): Result<Unit> {

        return try {

            taskRef
                .document(task.id)
                .set(task)
                .await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)

        }

    }

    suspend fun getAllTasks():
            List<DailyTaskModel> {

        return try {

            val snapshot =
                taskRef.get().await()

            snapshot.documents.mapNotNull {

                it.toObject(
                    DailyTaskModel::class.java
                )

            }

        } catch (e: Exception) {

            emptyList()

        }

    }

    suspend fun deleteTask(
        id: String
    ) {

        taskRef
            .document(id)
            .delete()
            .await()

    }

}
