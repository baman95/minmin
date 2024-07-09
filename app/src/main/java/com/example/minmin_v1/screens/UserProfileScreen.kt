package com.example.minmin_v1.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.minmin_v1.components.ClassDropdown
import com.example.minmin_v1.components.DatePickerField
import com.example.minmin_v1.components.GenderDropdown
import com.example.minmin_v1.components.ProfileImagePicker

@Composable
fun UserProfileScreen(navController: NavController) {
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
            try {
                navController.navigate("home")
            } catch (e: Exception) {
                Log.e("UserProfileScreen", "Error navigating to home: ${e.localizedMessage}", e)
            }
        }) {
            Text("Save Profile")
        }
    }
}
