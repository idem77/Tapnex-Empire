package com.tapnexempire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapnexempire.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: AuthRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow(false)
    val loginState: StateFlow<Boolean> = _loginState

    fun loginWithGoogle(idToken: String) {

        viewModelScope.launch {

            try {
                val success = repo.handleGoogleLogin(idToken)
                _loginState.value = success

                println("🔥 LOGIN STATE 👉 $success")

            } catch (e: Exception) {
                println("❌ VIEWMODEL ERROR 👉 ${e.message}")
                _loginState.value = false
            }
        }
    }
}
