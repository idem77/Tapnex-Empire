package com.tapnexempire.security

import com.google.firebase.firestore.FirebaseFirestore

object AdminSecurity {

    private val db = FirebaseFirestore.getInstance()

    fun isAdmin(
        uid: String,
        onResult: (Boolean) -> Unit
    ) {

        db.collection("admins")
            .document(uid)
            .get()
            .addOnSuccessListener { doc ->

                val active =
                    doc.getBoolean("active") ?: false

                onResult(active)
            }
            .addOnFailureListener {
                onResult(false)
            }
    }
}
