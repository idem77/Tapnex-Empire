package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _isLoggedIn = MutableStateFlow(firebaseAuth.currentUser != null)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private val _authMessage = MutableStateFlow<String?>(null)
    val authMessage: StateFlow<String?> = _authMessage

    fun login(email: String, password: String) {
        viewModelScope.launch {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    _isLoggedIn.value = task.isSuccessful && firebaseAuth.currentUser != null
                    _authMessage.value = if (task.isSuccessful) {
                        "Login successful 🎉"
                    } else {
                        task.exception?.message ?: "Login failed 😞"
                    }
                }
        }
    }

    fun signup(email: String, password: String) {
        viewModelScope.launch {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    _isLoggedIn.value = task.isSuccessful && firebaseAuth.currentUser != null
                    _authMessage.value = if (task.isSuccessful) {
                        "Account created successfully 🥳"
                    } else {
                        task.exception?.message ?: "Signup failed 😢"
                    }
                }
        }
    }

    fun logout() {
        firebaseAuth.signOut()
        _isLoggedIn.value = false
        _authMessage.value = "Logged out successfully 👋"
    }
}
