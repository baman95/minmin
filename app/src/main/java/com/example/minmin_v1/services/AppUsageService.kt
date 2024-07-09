package com.example.minmin_v1.services

import android.app.Service
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log

class AppUsageService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        monitorAppUsage()
        return START_STICKY
    }

    private fun monitorAppUsage() {
        val usageStatsManager = getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val currentTime = System.currentTimeMillis()
        val usageEvents = usageStatsManager.queryEvents(currentTime - 1000 * 60 * 60, currentTime)
        while (usageEvents.hasNextEvent()) {
            val event = android.app.usage.UsageEvents.Event()
            usageEvents.getNextEvent(event)
            Log.d("AppUsageService", "Event: ${event.packageName}, Type: ${event.eventType}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("AppUsageService", "App Usage Service Destroyed")
    }
}
