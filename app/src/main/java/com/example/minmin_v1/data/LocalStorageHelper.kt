package com.example.minmin_v1.data

import android.content.Context
import android.content.SharedPreferences
import com.example.minmin_v1.model.UserProfile
import com.google.gson.Gson

object LocalStorageHelper {
    private const val USER_PROFILE_KEY = "user_profile"

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }

    fun saveUserProfile(userProfile: UserProfile) {
        val editor = sharedPreferences.edit()
        val json = Gson().toJson(userProfile)
        editor.putString(USER_PROFILE_KEY, json)
        editor.apply()
    }

    fun getUserProfile(): UserProfile? {
        val json = sharedPreferences.getString(USER_PROFILE_KEY, null) ?: return null
        return Gson().fromJson(json, UserProfile::class.java)
    }

    fun saveGender(gender: String) {
        // Save gender to shared preferences or database
    }

    fun saveClass(className: String) {
        // Save class to shared preferences or database
    }
}
