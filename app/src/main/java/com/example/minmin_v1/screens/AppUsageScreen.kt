package com.example.minmin_v1.screens

import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.minmin_v1.components.UsageStatsCard
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppUsageScreen(navController: NavController) {
    val context = LocalContext.current
    var usageStats by remember { mutableStateOf<List<UsageStats>>(emptyList()) }

    LaunchedEffect(Unit) {
        usageStats = getUsageStats(context)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("App Usage Monitoring") }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                Text(
                    text = "App Usage Stats:",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(usageStats) { stat ->
                        UsageStatsCard(stat)
                    }
                }
            }
        }
    )
}

private fun getUsageStats(context: Context): List<UsageStats> {
    val usageStatsManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
        context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
    } else {
        return emptyList()
    }

    val cal = Calendar.getInstance()
    cal.add(Calendar.DAY_OF_YEAR, -1)

    val endTime = System.currentTimeMillis()
    val startTime = cal.timeInMillis

    val usageStats = usageStatsManager.queryUsageStats(
        UsageStatsManager.INTERVAL_DAILY,
        startTime, endTime
    )

    Log.d("AppUsageScreen", "Usage stats retrieved: $usageStats")
    return usageStats
}
