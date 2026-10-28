package com.ecotrack.app.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = EcoGreenDarkPrimary,
    secondary = EcoAccentBlue,
    background = EcoBackgroundDark,
    surface = EcoSurfaceDark,
    onPrimary = EcoBackgroundDark,
    onSecondary = EcoTextPrimaryDark,
    onBackground = EcoTextPrimaryDark,
    onSurface = EcoTextPrimaryDark
)

private val LightColorScheme = lightColorScheme(
    primary = EcoGreenPrimary,
    secondary = EcoAccentBlue,
    background = EcoBackgroundLight,
    surface = EcoSurfaceLight,
    onPrimary = EcoSurfaceLight,
    onSecondary = EcoSurfaceLight,
    onBackground = EcoTextPrimary,
    onSurface = EcoTextPrimary
)

@Composable
fun EcoTrackTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
