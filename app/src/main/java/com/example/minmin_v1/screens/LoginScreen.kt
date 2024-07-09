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
fun LoginScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login")
        // Add Email and Password fields, and Login button
        // Use appropriate visual transformations for password fields
        // On successful login, navigate to home screen
        Button(onClick = {
            try {
                navController.navigate("home")
            } catch (e: Exception) {
                Log.e("LoginScreen", "Error navigating to home: ${e.localizedMessage}", e)
            }
        }, modifier = Modifier.padding(top = 16.dp)) {
            Text("Login")
        }
        Button(onClick = {
            try {
                navController.navigate("signup")
            } catch (e: Exception) {
                Log.e("LoginScreen", "Error navigating to signup: ${e.localizedMessage}", e)
            }
        }, modifier = Modifier.padding(top = 16.dp)) {
            Text("Signup")
        }
    }
}
