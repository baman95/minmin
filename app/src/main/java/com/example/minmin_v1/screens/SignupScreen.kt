package com.example.minmin_v1.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SignupScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Signup")
        // Add fields for name, email, password, and confirm password
        // Use appropriate visual transformations for password fields
        // On successful signup, navigate to login screen
        Button(onClick = {
            try {
                navController.navigate("login")
            } catch (e: Exception) {
                Log.e("SignupScreen", "Error navigating to login: ${e.localizedMessage}", e)
            }
        }, modifier = Modifier.padding(top = 16.dp)) {
            Text("Signup")
        }
    }
}
