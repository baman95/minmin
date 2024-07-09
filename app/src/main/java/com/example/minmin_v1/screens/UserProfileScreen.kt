package com.example.minmin_v1.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.minmin_v1.components.GenderDropdown
import com.example.minmin_v1.components.ClassDropdown
import com.example.minmin_v1.components.DatePickerField
import com.example.minmin_v1.components.ProfileImagePicker
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

data class UserProfile(
    val gender: String = "",
    val className: String = "",
    val dateOfBirth: String = ""
)

@Composable
fun UserProfileScreen(navController: NavController) {
    val context = LocalContext.current
    val user = FirebaseAuth.getInstance().currentUser
    val db = FirebaseFirestore.getInstance()
    var userProfile by remember { mutableStateOf(UserProfile()) }

    LaunchedEffect(user) {
        user?.let {
            db.collection("users").document(it.uid).get()
                .addOnSuccessListener { document ->
                    userProfile = document.toObject(UserProfile::class.java) ?: UserProfile()
                }
                .addOnFailureListener { e ->
                    Log.e("UserProfileScreen", "Error getting user profile", e)
                }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("User Profile")
        ProfileImagePicker()
        Spacer(modifier = Modifier.height(16.dp))
        GenderDropdown()
        Spacer(modifier = Modifier.height(16.dp))
        ClassDropdown()
        Spacer(modifier = Modifier.height(16.dp))
        DatePickerField()
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val updatedProfile = UserProfile(
                gender = userProfile.gender,
                className = userProfile.className,
                dateOfBirth = userProfile.dateOfBirth
            )
            user?.let {
                db.collection("users").document(it.uid).set(updatedProfile)
                    .addOnSuccessListener {
                        navController.navigate("home") {
                            popUpTo("splash") { inclusive = true }
                        }
                    }
                    .addOnFailureListener { e ->
                        Log.e("UserProfileScreen", "Error updating user profile", e)
                    }
            }
        }) {
            Text("Save Profile")
        }
    }
}
