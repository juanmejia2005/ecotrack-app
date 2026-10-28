package com.ecotrack.app

import android.app.Application
import com.google.firebase.FirebaseApp

/**
 * EcoTrack Application entry class.
 * Initializes Firebase core components and dependency hooks.
 */
class EcoTrackApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Firebase services
        FirebaseApp.initializeApp(this)
    }
}
