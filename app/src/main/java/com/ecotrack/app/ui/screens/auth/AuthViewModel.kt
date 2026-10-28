package com.ecotrack.app.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecotrack.app.data.repository.AuthRepository
import com.ecotrack.app.data.repository.FirebaseAuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val isRegisterMode: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)

class AuthViewModel(
    private val authRepository: AuthRepository = FirebaseAuthRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    init {
        // Auto-navigate if session already exists
        if (authRepository.getCurrentUser() != null) {
            _uiState.update { it.copy(isSuccess = true) }
        }
    }

    fun onEmailChanged(email: String) {
        _uiState.update { it.copy(email = email, errorMessage = null) }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password, errorMessage = null) }
    }

    fun toggleAuthMode() {
        _uiState.update {
            it.copy(
                isRegisterMode = !it.isRegisterMode,
                errorMessage = null
            )
        }
    }

    fun submitAuth() {
        val currentState = _uiState.value
        val email = currentState.email.trim()
        val password = currentState.password.trim()

        if (email.isBlank() || password.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Por favor ingresa tanto el correo como la contraseña.") }
            return
        }

        if (password.length < 6) {
            _uiState.update { it.copy(errorMessage = "La contraseña debe tener al menos 6 caracteres.") }
            return
        }

        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        viewModelScope.launch {
            val result = if (currentState.isRegisterMode) {
                authRepository.register(email, password)
            } else {
                authRepository.login(email, password)
            }

            result.fold(
                onSuccess = {
                    _uiState.update { it.copy(isLoading = false, isSuccess = true) }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.localizedMessage ?: "Error en la operación de autenticación."
                        )
                    }
                }
            )
        }
    }
}
