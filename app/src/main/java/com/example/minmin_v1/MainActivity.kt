package com.example.minmin_v1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.minmin_v1.navigation.AppNavigation
import com.example.minmin_v1.ui.theme.MinMinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MinMinTheme {
                val navController = rememberNavController()
                AppNavigation(navController = navController)
            }
        }
    }
}
