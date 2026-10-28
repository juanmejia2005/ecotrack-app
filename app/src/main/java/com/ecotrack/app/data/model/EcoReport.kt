package com.ecotrack.app.data.model

import com.google.firebase.Timestamp

/**
 * Domain entity representing an environmental incident or recycling spot reported by a citizen.
 * Directly maps to documents in Cloud Firestore collection: `eco_reports`.
 */
data class EcoReport(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val category: String = "General Waste",
    val location: String = "",
    val status: String = "Pending", // Pending, In Review, Resolved
    val userId: String = "",
    val userEmail: String = "",
    val timestamp: Timestamp = Timestamp.now()
) {
    /**
     * Converts domain model to a Firestore-compatible hash map.
     */
    fun toMap(): Map<String, Any> {
        return mapOf(
            "title" to title,
            "description" to description,
            "category" to category,
            "location" to location,
            "status" to status,
            "userId" to userId,
            "userEmail" to userEmail,
            "timestamp" to timestamp
        )
    }

    companion object {
        /**
         * Reconstructs an EcoReport object from Firestore document snapshot.
         */
        fun fromMap(id: String, map: Map<String, Any?>): EcoReport {
            return EcoReport(
                id = id,
                title = map["title"] as? String ?: "",
                description = map["description"] as? String ?: "",
                category = map["category"] as? String ?: "General Waste",
                location = map["location"] as? String ?: "",
                status = map["status"] as? String ?: "Pending",
                userId = map["userId"] as? String ?: "",
                userEmail = map["userEmail"] as? String ?: "",
                timestamp = map["timestamp"] as? Timestamp ?: Timestamp.now()
            )
        }
    }
}
