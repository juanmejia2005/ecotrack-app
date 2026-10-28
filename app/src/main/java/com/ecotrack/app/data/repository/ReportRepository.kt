package com.ecotrack.app.data.repository

import com.ecotrack.app.data.model.EcoReport
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

/**
 * Interface contract for managing EcoReport entities in the cloud database.
 */
interface ReportRepository {
    fun observeReports(): Flow<List<EcoReport>>
    suspend fun createReport(report: EcoReport): Result<String>
    suspend fun updateReportStatus(reportId: String, newStatus: String): Result<Unit>
}

/**
 * Cloud Firestore implementation of [ReportRepository].
 * Provides real-time synchronization via Kotlin Coroutines callbackFlow.
 */
class FirebaseReportRepository(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) : ReportRepository {

    private val collection = firestore.collection("eco_reports")

    /**
     * Observes real-time changes to the eco_reports collection, ordered chronologically descending.
     */
    override fun observeReports(): Flow<List<EcoReport>> = callbackFlow {
        val listener = collection
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val reports = snapshot.documents.map { doc ->
                        EcoReport.fromMap(doc.id, doc.data ?: emptyMap())
                    }
                    trySend(reports)
                }
            }

        awaitClose { listener.remove() }
    }

    /**
     * Inserts a new EcoReport record into Cloud Firestore.
     */
    override suspend fun createReport(report: EcoReport): Result<String> {
        return try {
            val docRef = collection.add(report.toMap()).await()
            Result.success(docRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Updates status flags (e.g., Pending -> In Review -> Resolved).
     */
    override suspend fun updateReportStatus(reportId: String, newStatus: String): Result<Unit> {
        return try {
            collection.document(reportId).update("status", newStatus).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
