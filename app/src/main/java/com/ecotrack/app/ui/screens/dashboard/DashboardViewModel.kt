package com.ecotrack.app.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecotrack.app.data.model.EcoReport
import com.ecotrack.app.data.repository.AuthRepository
import com.ecotrack.app.data.repository.FirebaseAuthRepository
import com.ecotrack.app.data.repository.FirebaseReportRepository
import com.ecotrack.app.data.repository.ReportRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class DashboardUiState(
    val reports: List<EcoReport> = emptyList(),
    val selectedCategory: String = "Todos",
    val isLoading: Boolean = true,
    val userEmail: String = ""
)

class DashboardViewModel(
    private val reportRepository: ReportRepository = FirebaseReportRepository(),
    private val authRepository: AuthRepository = FirebaseAuthRepository()
) : ViewModel() {

    private val _selectedCategory = MutableStateFlow("Todos")
    private val _isLoading = MutableStateFlow(true)

    val uiState: StateFlow<DashboardUiState> = combine(
        reportRepository.observeReports(),
        _selectedCategory,
        _isLoading
    ) { reportsList, category, loading ->
        val filtered = if (category.equals("All", ignoreCase = true) || category.equals("Todos", ignoreCase = true)) {
            reportsList
        } else {
            reportsList.filter { report ->
                isCategoryMatch(report.category, category)
            }
        }
        val currentUser = authRepository.getCurrentUser()

        DashboardUiState(
            reports = filtered,
            selectedCategory = category,
            isLoading = false,
            userEmail = currentUser?.email.orEmpty()
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DashboardUiState()
    )

    private fun isCategoryMatch(reportCategory: String, filter: String): Boolean {
        if (reportCategory.equals(filter, ignoreCase = true)) return true
        return when (filter) {
            "Puntos de Reciclaje" -> reportCategory.equals("Recycling Station", ignoreCase = true)
            "Vertedero Ilegal" -> reportCategory.equals("Illegal Dumping", ignoreCase = true)
            "Residuos Electrónicos" -> reportCategory.equals("Electronic Waste", ignoreCase = true)
            "Basura General" -> reportCategory.equals("General Waste", ignoreCase = true)
            "Compost Orgánico" -> reportCategory.equals("Organic Compost", ignoreCase = true)
            "Recycling Station" -> reportCategory.equals("Puntos de Reciclaje", ignoreCase = true)
            "Illegal Dumping" -> reportCategory.equals("Vertedero Ilegal", ignoreCase = true)
            "Electronic Waste" -> reportCategory.equals("Residuos Electrónicos", ignoreCase = true)
            "General Waste" -> reportCategory.equals("Basura General", ignoreCase = true)
            "Organic Compost" -> reportCategory.equals("Compost Orgánico", ignoreCase = true)
            else -> false
        }
    }

    fun onCategorySelected(category: String) {
        _selectedCategory.value = category
    }

    fun logout(onLoggedOut: () -> Unit) {
        authRepository.logout()
        onLoggedOut()
    }

    fun markStatus(reportId: String, newStatus: String) {
        viewModelScope.launch {
            reportRepository.updateReportStatus(reportId, newStatus)
        }
    }
}
