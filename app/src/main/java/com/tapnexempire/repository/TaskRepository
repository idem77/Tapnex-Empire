package com.tapnexempire.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    suspend fun getTasks() =
        firestore.collection("tasks").get().await()
}
