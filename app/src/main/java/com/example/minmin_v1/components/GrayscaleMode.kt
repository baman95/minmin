package com.example.minmin_v1.components

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun GrayscaleModeButton() {
    val context = LocalContext.current
    var isGrayscaleEnabled by remember { mutableStateOf(isGrayscaleModeEnabled(context)) }

    fun setGrayscaleMode(enable: Boolean) {
        try {
            if (enable) {
                context.startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
                Toast.makeText(context, "Enable 'Custom Accessibility Service' in Accessibility settings", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Disable 'Custom Accessibility Service' in Accessibility settings", Toast.LENGTH_LONG).show()
            }
            isGrayscaleEnabled = enable
        } catch (e: Exception) {
            Toast.makeText(context, "Unable to change grayscale mode: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    Button(
        onClick = { setGrayscaleMode(!isGrayscaleEnabled) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(text = if (isGrayscaleEnabled) "Disable Grayscale Mode" else "Enable Grayscale Mode")
    }
}

fun isGrayscaleModeEnabled(context: Context): Boolean {
    // Check if custom accessibility service is enabled
    val enabledServices = Settings.Secure.getString(context.contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES)
    return enabledServices?.contains(context.packageName) ?: false
}
