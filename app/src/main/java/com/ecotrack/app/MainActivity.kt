package com.ecotrack.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ecotrack.app.ui.navigation.EcoTrackNavGraph
import com.ecotrack.app.ui.theme.EcoTrackTheme

/**
 * Single-Activity container for EcoTrack.
 * Hosts the Jetpack Compose navigation graph and Material 3 design system.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcoTrackTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EcoTrackNavGraph()
                }
            }
        }
    }
}
