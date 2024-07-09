package com.example.minmin_v1

import android.content.Context
import android.provider.Settings
import android.util.Log

fun setGrayscaleMode(context: Context, enable: Boolean) {
    try {
        val contentResolver = context.contentResolver
        Settings.Secure.putInt(contentResolver, "accessibility_display_daltonizer_enabled", if (enable) 1 else 0)
        Settings.Secure.putInt(contentResolver, "accessibility_display_daltonizer", if (enable) 0 else -1)
    } catch (e: Exception) {
        Log.e("GrayscaleMode", "Failed to set grayscale mode", e)
    }
}
