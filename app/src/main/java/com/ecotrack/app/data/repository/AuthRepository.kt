package com.ecotrack.app.data.repository

import com.ecotrack.app.data.model.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

/**
 * Interface defining authentication contracts for the application.
 */
interface AuthRepository {
    suspend fun login(email: String, pass: String): Result<User>
    suspend fun register(email: String, pass: String): Result<User>
    fun getCurrentUser(): User?
    fun logout()
}

/**
 * Production implementation of [AuthRepository] utilizing Firebase Authentication.
 */
class FirebaseAuthRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) : AuthRepository {

    override suspend fun login(email: String, pass: String): Result<User> {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email.trim(), pass.trim()).await()
            val firebaseUser = authResult.user
            if (firebaseUser != null) {
                Result.success(
                    User(
                        uid = firebaseUser.uid,
                        email = firebaseUser.email.orEmpty(),
                        displayName = firebaseUser.displayName
                    )
                )
            } else {
                Result.failure(Exception("Authentication failed: User profile null."))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(email: String, pass: String): Result<User> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email.trim(), pass.trim()).await()
            val firebaseUser = authResult.user
            if (firebaseUser != null) {
                Result.success(
                    User(
                        uid = firebaseUser.uid,
                        email = firebaseUser.email.orEmpty(),
                        displayName = firebaseUser.displayName
                    )
                )
            } else {
                Result.failure(Exception("Registration failed: User profile null."))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getCurrentUser(): User? {
        val firebaseUser = auth.currentUser ?: return null
        return User(
            uid = firebaseUser.uid,
            email = firebaseUser.email.orEmpty(),
            displayName = firebaseUser.displayName
        )
    }

    override fun logout() {
        auth.signOut()
    }
}
