package com.example.minmin_v1.data

import android.net.Uri
import com.example.minmin_v1.model.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.util.UUID

class FirebaseHelper {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()

    fun login(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onError(task.exception?.message ?: "Login failed")
                }
            }
    }

    fun signup(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onError(task.exception?.message ?: "Signup failed")
                }
            }
    }

    suspend fun getUserProfile(): UserProfile {
        val user = auth.currentUser ?: throw Exception("User not logged in")
        val document = db.collection("users").document(user.uid).get().await()
        return document.toObject(UserProfile::class.java) ?: UserProfile()
    }

    fun saveUserProfile(userProfile: UserProfile, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val user = auth.currentUser ?: return onFailure("User not logged in")
        db.collection("users").document(user.uid).set(userProfile)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it.message ?: "Failed to save profile") }
    }

    fun uploadProfileImage(uri: Uri, onSuccess: (String) -> Unit, onFailure: (Exception) -> Unit) {
        val user = auth.currentUser ?: return onFailure(Exception("User not logged in"))
        val storageRef = storage.reference.child("profile_images/${user.uid}/${UUID.randomUUID()}.jpg")
        val uploadTask = storageRef.putFile(uri)
        uploadTask.addOnSuccessListener {
            storageRef.downloadUrl.addOnSuccessListener { downloadUrl ->
                onSuccess(downloadUrl.toString())
            }.addOnFailureListener { exception ->
                onFailure(exception)
            }
        }.addOnFailureListener { exception ->
            onFailure(exception)
        }
    }

    fun getSuggestions(onSuccess: (Suggestions) -> Unit) {
        // Fetch suggestions from Firebase and call onSuccess with the data
    }
}

data class Suggestions(
    val interestSuggestions: List<String> = emptyList(),
    val subjects: List<String> = emptyList(),
    val games: List<String> = emptyList()
)
