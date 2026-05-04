package com.tapnexempire.ui.auth

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val userId: String) : AuthState()
    data class Error(val message: String) : AuthState()
}

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun register(email: String, password: String) {
        _authState.value = AuthState.Loading

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val uid = auth.currentUser?.uid ?: ""

                    // 🔥 USER SAVE
                    val userMap = mapOf(
                        "email" to email
                    )

                    db.collection("users")
                        .document(uid)
                        .set(userMap)

                    // 🔥 WALLET CREATE (IMPORTANT)
                    val walletMap = mapOf(
                        "depositCoins" to 0,
                        "bonusCoins" to 500, // 🎁 welcome bonus
                        "withdrawableCoins" to 0
                    )

                    db.collection("wallets")
                        .document(uid)
                        .set(walletMap)

                    _authState.value = AuthState.Success(uid)

                } else {
                    _authState.value =
                        AuthState.Error(task.exception?.message ?: "Signup failed")
                }
            }
    }

    fun login(email: String, password: String) {
        _authState.value = AuthState.Loading

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val uid = auth.currentUser?.uid ?: ""

                    // 🔥 CHECK WALLET EXISTS
                    db.collection("wallets")
                        .document(uid)
                        .get()
                        .addOnSuccessListener { doc ->

                            if (!doc.exists()) {

                                val walletMap = mapOf(
                                    "depositCoins" to 0,
                                    "bonusCoins" to 500,
                                    "withdrawableCoins" to 0
                                )

                                db.collection("wallets")
                                    .document(uid)
                                    .set(walletMap)
                            }

                            _authState.value = AuthState.Success(uid)
                        }

                } else {
                    _authState.value =
                        AuthState.Error(task.exception?.message ?: "Login failed")
                }
            }
    }

    fun logout() {
        auth.signOut()
        _authState.value = AuthState.Idle
    }
}
