package com.ecotrack.app.ui.screens.create_report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecotrack.app.data.model.EcoReport
import com.ecotrack.app.data.repository.AuthRepository
import com.ecotrack.app.data.repository.FirebaseAuthRepository
import com.ecotrack.app.data.repository.FirebaseReportRepository
import com.ecotrack.app.data.repository.ReportRepository
import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CreateReportUiState(
    val title: String = "",
    val description: String = "",
    val category: String = "Recycling Station",
    val location: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)

class CreateReportViewModel(
    private val reportRepository: ReportRepository = FirebaseReportRepository(),
    private val authRepository: AuthRepository = FirebaseAuthRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateReportUiState())
    val uiState: StateFlow<CreateReportUiState> = _uiState.asStateFlow()

    fun onTitleChanged(value: String) {
        _uiState.update { it.copy(title = value, errorMessage = null) }
    }

    fun onDescriptionChanged(value: String) {
        _uiState.update { it.copy(description = value, errorMessage = null) }
    }

    fun onCategoryChanged(value: String) {
        _uiState.update { it.copy(category = value) }
    }

    fun onLocationChanged(value: String) {
        _uiState.update { it.copy(location = value, errorMessage = null) }
    }

    fun submitReport() {
        val state = _uiState.value
        val title = state.title.trim()
        val description = state.description.trim()
        val location = state.location.trim()

        if (title.isBlank() || description.isBlank() || location.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Por favor completa todos los campos obligatorios.") }
            return
        }

        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        viewModelScope.launch {
            val user = authRepository.getCurrentUser()
            val report = EcoReport(
                title = title,
                description = description,
                category = state.category,
                location = location,
                status = "Pending",
                userId = user?.uid.orEmpty(),
                userEmail = user?.email.orEmpty(),
                timestamp = Timestamp.now()
            )

            val result = reportRepository.createReport(report)
            result.fold(
                onSuccess = {
                    _uiState.update { it.copy(isLoading = false, isSuccess = true) }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.localizedMessage ?: "No se pudo registrar el eco-reporte. Intenta nuevamente."
                        )
                    }
                }
            )
        }
    }
}
