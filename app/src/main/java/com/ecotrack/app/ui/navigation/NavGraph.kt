package com.ecotrack.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ecotrack.app.ui.screens.auth.AuthScreen
import com.ecotrack.app.ui.screens.create_report.CreateReportScreen
import com.ecotrack.app.ui.screens.dashboard.DashboardScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun EcoTrackNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = if (FirebaseAuth.getInstance().currentUser != null) {
        Screen.Dashboard.route
    } else {
        Screen.Auth.route
    }
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Auth.route) {
            AuthScreen(
                onAuthSuccess = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Auth.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Dashboard.route) {
            DashboardScreen(
                onNavigateToCreate = {
                    navController.navigate(Screen.CreateReport.route)
                },
                onLogout = {
                    navController.navigate(Screen.Auth.route) {
                        popUpTo(Screen.Dashboard.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.CreateReport.route) {
            CreateReportScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
