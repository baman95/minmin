package com.example.minmin_v1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.minmin_v1.screens.HomeScreen
import com.example.minmin_v1.screens.LoginScreen
import com.example.minmin_v1.screens.SignupScreen
import com.example.minmin_v1.screens.SplashScreen
import com.example.minmin_v1.screens.UserProfileScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignupScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("profile") { UserProfileScreen(navController) }
    }
}
