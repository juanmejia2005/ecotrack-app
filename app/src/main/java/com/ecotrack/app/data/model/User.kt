package com.ecotrack.app.data.model

/**
 * Model representing an authenticated user session.
 */
data class User(
    val uid: String,
    val email: String,
    val displayName: String? = null
)
