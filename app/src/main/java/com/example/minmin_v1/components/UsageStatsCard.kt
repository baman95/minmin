package com.example.minmin_v1.components

import android.app.usage.UsageStats
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun UsageStatsCard(usageStats: UsageStats) {
    val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    val lastTimeUsed = dateFormat.format(Date(usageStats.lastTimeUsed))
    val totalTimeInForeground = dateFormat.format(Date(usageStats.totalTimeInForeground))

    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        modifier = Modifier.padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Package Name: ${usageStats.packageName}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Last Time Used: $lastTimeUsed", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Total Time in Foreground: $totalTimeInForeground", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
