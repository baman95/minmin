package com.example.minmin_v1.data

import android.content.Context

class DataHelper(private val context: Context) {

    fun getGenders(): List<String> {
        return listOf("Male", "Female", "Other")
    }

    fun getClasses(): List<String> {
        return listOf("Class 1", "Class 2", "Class 3")
    }
}
