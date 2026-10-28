package com.ecotrack.app.ui.navigation

/**
 * Navigation destination routes in the EcoTrack application.
 */
sealed class Screen(val route: String) {
    data object Auth : Screen("auth_screen")
    data object Dashboard : Screen("dashboard_screen")
    data object CreateReport : Screen("create_report_screen")
}
